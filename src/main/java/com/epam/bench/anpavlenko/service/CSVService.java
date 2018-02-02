package com.epam.bench.anpavlenko.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;

import com.epam.bench.anpavlenko.entity.Order;

/**
 * @author an.pavlenko
 */
public interface CSVService {

  Collection<Order> parseCSVToOrder(String filePath) throws FileNotFoundException;

  Collection<Order> parseCSVToOrder(File file) throws FileNotFoundException;
}
