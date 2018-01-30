package com.epam.banch.anpavlenko.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;

import com.epam.banch.anpavlenko.entity.Order;

/**
 * @author an.pavlenko
 */
public interface CSVService {

  Collection<Order> parseCSVToOrder(String filePath) throws FileNotFoundException;

  Collection<Order> parseCSVToOrder(File file) throws FileNotFoundException;
}
