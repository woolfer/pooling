package com.epam.banch.anpavlenko.service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.banch.anpavlenko.entity.Order;
import com.epam.banch.anpavlenko.repository.OrderRepository;

/**
 * Unit test for {@link OrderServiceImpl}.
 *
 * @author an.pavlenko
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

  @Mock
  private OrderRepository repository;
  @InjectMocks
  private OrderServiceImpl service;

  @Test
  public void testGetAll() {
    List<Order> ordersList = Arrays.asList(new Order());
    Mockito.when(repository.findAll()).thenReturn(ordersList);

    List<Order> result = service.getAll();

    Mockito.verify(repository).findAll();
    Assert.assertEquals("Unexpected result", ordersList, result);
  }

  @Test
  public void testCreateOrder() {
    Order order = Mockito.mock(Order.class);
    Mockito.when(repository.save(order)).thenReturn(order);

    Order result = service.createOrder(order);

    Mockito.verify(repository).save(order);
    Assert.assertEquals("Unexpected result", order, result);
  }

  @Test
  public void testUpdateOrder() {
    Order order = Mockito.mock(Order.class);
    service.updateOrder(order);

    Mockito.verify(repository).save(order);
  }

  @Test
  public void testDeleteOrder() {
    Long orderId = 4586L;
    service.deleteOrder(orderId);

    Mockito.verify(repository).delete(orderId);
  }
}
