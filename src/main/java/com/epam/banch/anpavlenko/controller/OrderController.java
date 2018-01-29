package com.epam.banch.anpavlenko.controller;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.banch.anpavlenko.entity.Order;
import com.epam.banch.anpavlenko.service.OrderService;

/**
 * @author an.pavlenko
 */
@RestController
public class OrderController {

  @Autowired
  private OrderService orderService;

  @RequestMapping("/orders")
  public Collection<Order> getAllOrders() {
    return orderService.getAll();
  }

  @RequestMapping("/create") //I know that it's not correct but it was added to fast order creation, just to test!!!
  public void create(@RequestParam String name) {
    Order order = new Order();
    order.setName(name);
    order.setCreatedDate(new Date());
    orderService.createOrder(order);
  }
}
