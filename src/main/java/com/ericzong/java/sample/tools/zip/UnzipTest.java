package com.ericzong.java.sample.tools.zip;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipTest {

    private static final String UN_PREFIX = "_unzip_";
    public static final String _7Z_PATH = "/zip/test.7z";
    public static final String RAR_PATH = "/zip/test.rar";
    private static final String ZIP_PATH = "/zip/test.zip";
    private static final String TAR_PATH = "/zip/test.tar";
    public static final String BZ2_PATH = "/zip/test.tar.bz2";
    public static final String GZ_PATH = "/zip/test.tar.gz";
    public static final String XZ_PATH = "/zip/test.tar.xz";

    @Test
    public void testUn7z() throws IOException {
        String filepath = getClass().getResource(_7Z_PATH).getFile();
        File file = new File(filepath);
        File unzipRoot = Files.createTempDirectory(UN_PREFIX).toFile();
        System.out.println(unzipRoot.getAbsolutePath());

        try (SevenZFile zFile = new SevenZFile(file)) {
            SevenZArchiveEntry entry;
            while ((entry = zFile.getNextEntry()) != null) {
                String filename = entry.getName();
                if (entry.isDirectory()) {
                    File currentDir = new File(unzipRoot, filename);
                    if (!currentDir.exists()) {
                        currentDir.mkdirs();
                    }
                } else {
                    File currentFile = new File(unzipRoot, filename);
                    File parent = currentFile.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    try (FileOutputStream fileOutputStream = new FileOutputStream(currentFile)) {
                        int data;
                        while ((data = zFile.read()) != -1) {
                            fileOutputStream.write(data);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testUnRar() throws IOException, RarException {
        String filepath = getClass().getResource(RAR_PATH).getFile();
        File file = new File(filepath);
        File unzipRoot = Files.createTempDirectory(UN_PREFIX).toFile();
        System.out.println(unzipRoot.getAbsolutePath());

        try (Archive rarFile = new Archive(file, null)) {
            FileHeader header;
            while ((header = rarFile.nextFileHeader()) != null) {
                String filename = header.getFileNameW();
                if (header.isDirectory()) {
                    File currentDir = new File(unzipRoot, filename);
                    if (!currentDir.exists()) {
                        currentDir.mkdirs();
                    }
                } else {
                    File currentFile = new File(unzipRoot, filename);
                    File parent = currentFile.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    try (OutputStream outputStream = new FileOutputStream(currentFile)) {
                        rarFile.extractFile(header, outputStream);
                    }
                }
            }
        }
    }

    @Test
    public void testUnZip() throws IOException {
        String filepath = getClass().getResource(ZIP_PATH).getFile();
        Path unzipRootPath = Files.createTempDirectory(UN_PREFIX);
        File unzipRoot = unzipRootPath.toFile();
        System.out.println(unzipRoot.getAbsolutePath());

        try (ZipFile zipFile = new ZipFile(filepath, Charset.defaultCharset())) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String filename = entry.getName();
                if (entry.isDirectory()) {
                    File currentDir = new File(unzipRoot, filename);
                    if (!currentDir.exists()) {
                        currentDir.mkdirs();
                    }
                } else {
                    File currentFile = new File(unzipRoot, filename);
                    currentFile.createNewFile();
                    try (InputStream inputStream = zipFile.getInputStream(entry);
                         FileOutputStream fileOutputStream = new FileOutputStream(currentFile);
                         BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                        int data;
                        while ((data = inputStream.read()) != -1) {
                            bufferedOutputStream.write(data);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testUnTar() throws IOException {
        String filepath = getClass().getResource(TAR_PATH).getFile();
        Path unzipRootPath = Files.createTempDirectory(UN_PREFIX);
        File unzipRoot = unzipRootPath.toFile();
        System.out.println(unzipRoot.getAbsolutePath());

        try (FileInputStream fileInputStream = new FileInputStream(filepath);
             TarInputStream tarInputStream = new TarInputStream(fileInputStream)) {
            TarEntry entry;
            while ((entry = tarInputStream.getNextEntry()) != null) {
                String filename = entry.getName();
                if (entry.isDirectory()) {
                    File currentDir = new File(unzipRoot, filename);
                    if (!currentDir.exists()) {
                        currentDir.mkdirs();
                    }
                } else {
                    File currentFile = new File(unzipRoot, filename);
                    currentFile.createNewFile();
                    try (FileOutputStream fileOutputStream = new FileOutputStream(currentFile)) {
                        tarInputStream.copyEntryContents(fileOutputStream);
                    }
                }
            }
        }
    }

    @Test
    public void testUnBz2() throws IOException {
        String filepath = getClass().getResource(BZ2_PATH).getFile();
        File tarTemp = Files.createTempFile(UN_PREFIX, ".tar").toFile();

        System.out.println("tar temp file:" + tarTemp.getAbsolutePath());

        // 注意：输出的是 .tar 文件
        try (FileInputStream fileInputStream = new FileInputStream(filepath);
             BZip2CompressorInputStream inputStream = new BZip2CompressorInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(tarTemp)) {
            int data;
            while ((data = inputStream.read()) != -1) {
                fileOutputStream.write(data);
            }
        }
    }

    @Test
    public void testUnGz() throws IOException {
        String filepath = getClass().getResource(GZ_PATH).getFile();
        File tarTemp = Files.createTempFile(UN_PREFIX, ".tar").toFile();

        System.out.println("tar temp file:" + tarTemp.getAbsolutePath());

        try (FileInputStream fileInputStream = new FileInputStream(filepath);
             GZIPInputStream inputStream = new GZIPInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(tarTemp)) {
            int data;
            while ((data = inputStream.read()) != -1) {
                fileOutputStream.write(data);
            }
        }
    }

    @Test
    public void testUnXz() throws IOException {
        String filepath = getClass().getResource(XZ_PATH).getFile();
        File tarTemp = Files.createTempFile(UN_PREFIX, ".tar").toFile();

        System.out.println("tar temp file:" + tarTemp.getAbsolutePath());

        // 注意：需要 XZ For Java: org.tukaani
        try (FileInputStream fileInputStream = new FileInputStream(filepath);
             XZCompressorInputStream inputStream = new XZCompressorInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(tarTemp)) {
            int data;
            while ((data = inputStream.read()) != -1) {
                fileOutputStream.write(data);
            }
        }
    }

}
