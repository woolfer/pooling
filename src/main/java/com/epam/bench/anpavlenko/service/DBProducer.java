package com.epam.bench.anpavlenko.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.epam.bench.anpavlenko.entity.PreOrder;

/**
 * @author an.pavlenko
 */
@Service
public class DBProducer implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(DBProducer.class);

  @Autowired
  private BlockingQueue queue;

  @Autowired
  private PreOrderService service;

  @Value("${db.check.time:1000}")
  private Long timeout;

  @Override
  public void run() {
    while (true) {
      try {
        //TODO hope by putting Thread.sleep at the  end of DBConsumer service and at the begining of DBProducer service you are not trying to get some kind of syncronization of this two thread.
        Thread.sleep(timeout);
        LOG.debug("Producer waked up");
        List<PreOrder> uncheckedOrder = service.getUncheckedOrder();
        if (!uncheckedOrder.isEmpty()) {
          LOG.debug("Unchecked preOrder list size {}", uncheckedOrder.size());
          queue.put(uncheckedOrder);
        }
      } catch (InterruptedException e) {
        //TODO again you have logger but using printstacktrace
        e.printStackTrace();
      }
    }
  }
}
