package com.epam.bench.anpavlenko.factory.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.epam.bench.anpavlenko.entity.Order;
import com.epam.bench.anpavlenko.entity.PreOrder;
import com.epam.bench.anpavlenko.factory.OrderFactory;

/**
 * @author an.pavlenko
 */
//TODO it is not factory nor factory method. more looks like builder, but I will again ask you a question why do you need to do so much new Order in your code. Move it to separate util class it doesn't deserve to be a separate service.
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
