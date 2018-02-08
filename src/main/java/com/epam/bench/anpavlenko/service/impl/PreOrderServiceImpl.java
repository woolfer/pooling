package com.epam.bench.anpavlenko.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.bench.anpavlenko.entity.PreOrder;
import com.epam.bench.anpavlenko.repository.PreOrderRepository;
import com.epam.bench.anpavlenko.service.PreOrderService;

/**
 * @author an.pavlenko
 */
//TODO see comments to OrderService
@Service
public class PreOrderServiceImpl implements PreOrderService {

  private static final Logger LOG = LoggerFactory.getLogger(PreOrderServiceImpl.class);

  @Autowired
  private PreOrderRepository repository;

  @Override
  public List<PreOrder> getAll() {
    return repository.findAll();
  }

  @Override
  public void updateOrder(PreOrder entity) {
    repository.save(entity);
  }

  @Override
  public List<PreOrder> getUncheckedOrder() {
    return repository.findByCheckedFalse();
  }

  @Override
  public void save(List<PreOrder> orderList) {
    repository.save(orderList);
  }
}
