package com.epam.bench.anpavlenko.service;

import java.util.List;
import java.util.concurrent.SynchronousQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.epam.bench.anpavlenko.entity.PreOrder;
import com.epam.bench.anpavlenko.factory.OrderFactory;

/**
 * @author an.pavlenko
 */
@Service
public class DBConsumer implements Runnable {
  private static final Logger LOG = LoggerFactory.getLogger(DBConsumer.class);
  @Autowired
  private SynchronousQueue queue;
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderFactory orderFactory;
  @Autowired
  private PreOrderService preOrderService;
  @Value("${db.check.time:1000}")
  private Long timeout;

  @Override
  public void run() {
    try {
      LOG.debug("Consumer waked up!");
      List<PreOrder> preOrderList = (List<PreOrder>) queue.take();
      if (!preOrderList.isEmpty()) {
        preOrderList.stream().map(orderFactory::createOrder).forEach(orderService::createOrder);
        preOrderList.stream().peek(preOrder -> preOrder.setChecked(true)).forEach(preOrderService::updateOrder);
      }
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
