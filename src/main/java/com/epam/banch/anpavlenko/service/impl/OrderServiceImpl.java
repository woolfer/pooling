package com.epam.banch.anpavlenko.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.banch.anpavlenko.entity.Order;
import com.epam.banch.anpavlenko.repository.OrderRepository;
import com.epam.banch.anpavlenko.service.OrderService;

/**
 * @author an.pavlenko
 */
@Service
public class OrderServiceImpl implements OrderService {

  private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

  @Autowired
  private OrderRepository repository;

  @Override
  public List<Order> getAll() {
    return repository.findAll();
  }

  @Override
  public Order createOrder(Order entity) {
    LOG.debug("Save order {}", entity);
    return repository.save(entity);
  }

  @Override
  public void updateOrder(Order entity) {
    LOG.debug("Update order {}", entity);
    repository.save(entity);
  }

  @Override
  public void deleteOrder(Long id) {
    LOG.debug("Delete order with ID {} ", id);
    repository.delete(id);
  }
}
