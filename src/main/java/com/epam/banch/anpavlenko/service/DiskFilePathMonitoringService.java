package com.epam.banch.anpavlenko.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

/**
 * @author an.pavlenko
 */
@Service
public class DiskFilePathMonitoringService implements Runnable{

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
    try {
      WatchKey watchKey = dir.register(watchService, event);
      keys.put(watchKey, dir);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void processEvent() throws IOException {
    WatchKey key;
    try {
      while ((key = watchService.take()) != null) {
        for (WatchEvent<?> event : key.pollEvents()) {
          //process
          System.out.println("GOT IT");
        }
        key.reset();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
      watchService.close();
    }
  }

  @Override
  public void run() {
    try {
      processEvent();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
