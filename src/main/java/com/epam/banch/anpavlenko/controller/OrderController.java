package com.epam.banch.anpavlenko.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.banch.anpavlenko.entity.Order;
import com.epam.banch.anpavlenko.repository.OrderRepository;

/**
 * @author an.pavlenko
 */
@RestController
public class OrderController {

  @Autowired
  private OrderRepository orderRepository;

  @RequestMapping("/orders")
  public Collection<Order> getAllOrders() {
    return orderRepository.findAll();
  }
}
