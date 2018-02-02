package com.epam.bench.anpavlenko.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.bench.anpavlenko.converter.OrderConverterImpl;
import com.epam.bench.anpavlenko.converter.POJOConverter;
import com.epam.bench.anpavlenko.entity.Order;
import com.epam.bench.anpavlenko.service.CSVService;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * @author an.pavlenko
 */
@Service
public class CSVServiceImpl implements CSVService {

  @Autowired
  @Qualifier("orderConverter")
  private POJOConverter converter;

  @Override
  public Collection<Order> parseCSVToOrder(String filePath) throws FileNotFoundException {
    return parce(new FileReader(filePath));
  }

  @Override
  public Collection<Order> parseCSVToOrder(File file) throws FileNotFoundException {
    return parce(new FileReader(file));
  }

  private Collection<Order> parce(FileReader fileReader) {
    List orderList = new CsvToBeanBuilder(fileReader).withType(OrderConverterImpl.OrderPOJO.class).build().parse();
    return converter.convert(orderList);
  }
}
