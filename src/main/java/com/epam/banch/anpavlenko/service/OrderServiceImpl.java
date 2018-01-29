package com.epam.banch.anpavlenko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.banch.anpavlenko.entity.Order;
import com.epam.banch.anpavlenko.repository.OrderRepository;

/**
 * @author an.pavlenko
 */
@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  private OrderRepository repository;

  @Override
  public List<Order> getAll() {
    return repository.findAll();
  }

  @Override
  public Order createOrder(Order entity) {
    return repository.save(entity);
  }

  @Override
  public void updateOrder(Order entity) {
    repository.save(entity);
  }

  @Override
  public void deleteOrder(Long id) {
    repository.delete(id);
  }
}
