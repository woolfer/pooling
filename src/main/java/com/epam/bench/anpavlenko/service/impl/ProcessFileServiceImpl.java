package com.epam.bench.anpavlenko.service.impl;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.epam.bench.anpavlenko.service.CSVService;
import com.epam.bench.anpavlenko.service.OrderService;
import com.epam.bench.anpavlenko.service.ProcessFileService;

/**
 * @author an.pavlenko
 */
@Service
public class ProcessFileServiceImpl implements ProcessFileService {

  private static final Logger LOG = LoggerFactory.getLogger(ProcessFileServiceImpl.class);

  @Value("${file.extension}")
  private String fileExtension;
  @Autowired
  private CSVService csvService;
  @Autowired
  private OrderService orderService;

  public void processFile(File file) {
    LOG.debug("Processing file {} with extension {}", file, fileExtension);
    if (FilenameUtils.getExtension(file.getName()).equals(fileExtension)) {
      try {
        LOG.debug("Creating the order from file {}", file.getAbsoluteFile());
        csvService.parseCSVToOrder(file).forEach(orderService::createOrder);
      } catch (FileNotFoundException e) {
        LOG.debug(e.getMessage());
      }
    }
  }

}
