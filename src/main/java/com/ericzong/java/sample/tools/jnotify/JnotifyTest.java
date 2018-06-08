package com.ericzong.java.sample.tools.jnotify;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;
import net.contentobjects.jnotify.JNotifyListener;

/**
 * @author zonglu
 */
public class JnotifyTest {
	@BeforeTest
	public static void loadLib()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		URL rootURL = JnotifyTest.class.getResource("/");
		String pathToAdd = rootURL.getFile().substring(1);
		boolean isAdded = false;
		final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
		usrPathsField.setAccessible(true);
		final String[] paths = (String[]) usrPathsField.get(null);
		for (String path : paths) {
			if (path.equals(pathToAdd)) {
				isAdded = true;
				break;
			}
		}
		if (!isAdded) {
			final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
			newPaths[newPaths.length - 1] = pathToAdd;
			usrPathsField.set(null, newPaths);
		}

		usrPathsField.setAccessible(false);
	}

	@Test
	public void test() throws JNotifyException, InterruptedException {
		// path to watch
		String path = "E:\\test";

		// watch mask, specify events you care about,
		// or JNotify.FILE_ANY for all events.
		int mask = JNotify.FILE_CREATED | JNotify.FILE_DELETED | JNotify.FILE_MODIFIED | JNotify.FILE_RENAMED;

		// watch subtree?
		boolean watchSubtree = true;

		// add actual watch
		int watchID = JNotify.addWatch(path, mask, watchSubtree, new Listener());

		// sleep a little, the application will exit if you
		// don't (watching is asynchronous), depending on your
		// application, this may not be required
		Thread.sleep(1000000);

		// to remove watch the watch
		boolean res = JNotify.removeWatch(watchID);
		if (!res) {
			// invalid watch ID specified.
		}

	}

	class Listener implements JNotifyListener {
		public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
			print("renamed " + rootPath + " : " + oldName + " -> " + newName);
		}

		public void fileModified(int wd, String rootPath, String name) {
			print("modified " + rootPath + " : " + name);
		}

		public void fileDeleted(int wd, String rootPath, String name) {
			print("deleted " + rootPath + " : " + name);
		}

		public void fileCreated(int wd, String rootPath, String name) {
			print("created " + rootPath + " : " + name);
		}

		void print(String msg) {
			System.err.println(msg);
		}
	}
}
