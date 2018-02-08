package com.epam.bench.anpavlenko.factory.impl;

import java.math.BigInteger;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.epam.bench.anpavlenko.entity.Order;
import com.epam.bench.anpavlenko.entity.PreOrder;
import com.epam.bench.anpavlenko.factory.OrderFactory;

/**
 * @author an.pavlenko
 */
public class OrderFactoryImplTest {

  private OrderFactory orderFactory = new OrderFactoryImpl();

  @Test
  public void testCreateOrder() {
    //TODO Frankly saying I'm already hate new Order
    PreOrder preOrder = new PreOrder();
    preOrder.setName("name");
    preOrder.setSurName("surName");
    preOrder.setCreatedDate(new Date());
    preOrder.setSum(BigInteger.TEN);

    Order order = orderFactory.createOrder(preOrder);

    Assert.assertEquals("Unexpected order name", preOrder.getName(), order.getName());
    Assert.assertEquals("Unexpected order surName", preOrder.getSurName(), order.getSurName());
    Assert.assertEquals("Unexpected order createdDate", preOrder.getCreatedDate(), order.getCreatedDate());
    Assert.assertEquals("Unexpected order sum", preOrder.getSum(), order.getSum());
  }

  @Test
  public void testCreateOrder_createdDateIsNull() {

    //TODO useless in case you will eliminate preorder
    Order order = orderFactory.createOrder(new PreOrder());

    Assert.assertNotNull("Created date should not be null", order.getCreatedDate());
  }
}
