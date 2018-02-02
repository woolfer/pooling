package com.epam.bench.anpavlenko.factory.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.epam.bench.anpavlenko.entity.Order;
import com.epam.bench.anpavlenko.entity.PreOrder;
import com.epam.bench.anpavlenko.factory.OrderFactory;

/**
 * @author an.pavlenko
 */
@Service
public class OrderFactoryImpl implements OrderFactory {

  @Override
  public Order createOrder(PreOrder preOrder) {
    Order order = new Order();
    order.setName(preOrder.getName());
    order.setSurName(preOrder.getSurName());
    order.setCreatedDate(preOrder.getCreatedDate() == null ? new Date() : preOrder.getCreatedDate());
    order.setSum(preOrder.getSum());
    return order;
  }
}
