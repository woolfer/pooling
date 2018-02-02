package com.epam.bench.anpavlenko.service;

import java.util.List;

import com.epam.bench.anpavlenko.entity.PreOrder;

/**
 * @author an.pavlenko
 */
public interface PreOrderService {

  List<PreOrder> getAll();

  void updateOrder(PreOrder entity);

  List<PreOrder> getUncheckedOrder();

  void save(List<PreOrder> orderList);
}
