package com.ericzong.java.sample.api.io;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.testng.annotations.Test;

/**
 * @author zonglu
 */
public class WatchTest {
	@Test
	public void test() throws IOException, InterruptedException {
		try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
			Path path = Paths.get("E:\\test");
			path.register(watcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE, OVERFLOW);

			WatchKey key;

			do {
				System.out.println("-----------------");
				key = watcher.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					Kind<?> kind = event.kind();
					Path eventPath = (Path) event.context();
					System.out.println(String.format("%d[%d] %s (%s)", event.count(), System.currentTimeMillis(), eventPath, kind));
				}
				System.out.println("-----------------");
			} while (key.reset());
		}
	}
}
