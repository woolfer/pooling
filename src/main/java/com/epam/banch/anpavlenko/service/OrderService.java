package com.epam.banch.anpavlenko.service;

import java.util.List;

import com.epam.banch.anpavlenko.entity.Order;

/**
 * @author an.pavlenko
 */
public interface OrderService {
  List<Order> getAll();

  Order createOrder(Order entity);

  void updateOrder(Order entity);

  void deleteOrder(Long id);
}
