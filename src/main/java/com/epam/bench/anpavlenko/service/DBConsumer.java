package com.epam.bench.anpavlenko.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;

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
  private BlockingQueue queue;
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
      //TODO hope by putting Thread.sleep at the  end of DBConsumer service and at the begining of DBProducer service you are not trying to get some kind of syncronization of this two thread.
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      //TODO again you have logger but using printstacktrace
      e.printStackTrace();
    }
  }
}
