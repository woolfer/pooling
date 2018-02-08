package com.epam.bench.anpavlenko.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author an.pavlenko
 */
@Service
public class DiskFilePathMonitoringService implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(DiskFilePathMonitoringService.class);

  @Autowired
  private ProcessFileService processFileService;

  private final WatchService watchService;
  private final Map<WatchKey, Path> keys;

  public DiskFilePathMonitoringService() throws IOException {
    this.watchService = FileSystems.getDefault().newWatchService();
    this.keys = new HashMap<WatchKey, Path>();
  }

  public void setTimeOut(Long timeOut, TimeUnit timeUnit) throws InterruptedException {
    this.watchService.poll(timeOut, timeUnit);
  }

  public void registerDirectory(String filePath, WatchEvent.Kind event) {
    Path dir = Paths.get(filePath);
    LOG.debug("Register directory - " + dir);
    try {
      WatchKey watchKey = dir.register(watchService, event);
      keys.put(watchKey, dir);
    } catch (IOException e) {
      LOG.debug(e.getMessage());
    }
  }

  private void processEvent() throws IOException {
    WatchKey key;
    try {
      while ((key = watchService.take()) != null) {
        for (WatchEvent<?> event : key.pollEvents()) {
          //process
          Path path = ((WatchEvent<Path>) event).context();
          LOG.debug("GOT file " + path);
          LOG.debug("Event " + event.kind());
          if (StandardWatchEventKinds.ENTRY_CREATE == event.kind()) {
            LOG.debug("ENTRY_CREATED");
            Path dir = keys.get(key);
            File file = dir.resolve(path).toFile();
            LOG.debug("File {} exists - {}", file, file.exists());
            processFileService.processFile(file);
          }
        }
        key.reset();
      }
    } catch (Exception e) {
      LOG.debug(e.getMessage());
      watchService.close();
    }
  }

  @Override
  public void run() {
    try {
      processEvent();
    } catch (IOException e) {
      LOG.debug(e.getMessage());
    }
  }
}
