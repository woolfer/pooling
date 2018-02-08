package com.epam.bench.anpavlenko.repository;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.epam.bench.anpavlenko.Application;
import com.epam.bench.anpavlenko.entity.Order;

/**
 * Integration test for {@link OrderRepository}
 *
 * @author an.pavlenko
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = Application.class)
public class OrderRepositoryIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private OrderRepository repository;

  //TODO empty?
  @Before
  public void init() {

  }

  @After
  public void clearDb() {
    //TODO better do it in before
    repository.deleteAll();
  }

  @Test
  public void testFindAll() {
    List<Order> expectedList = prepareTestData().stream().map(entityManager::persist).collect(Collectors.toList());
    entityManager.flush();

    List<Order> result = repository.findAll();

    Assert.assertEquals("Unexpected result", expectedList, result);
  }


  @Test
  public void testSave() {
    //TODO seriously? new Order?
    Order newOrder = new Order();
    newOrder.setName("Don");
    newOrder.setSurName("Jon");
    newOrder.setCreatedDate(new Date());
    newOrder.setSum(BigInteger.TEN);

    Order result = repository.save(newOrder);

    Assert.assertNotNull("Id should not be null", result.getId());
    Assert.assertEquals("Unexpected name value", newOrder.getName(), result.getName());
    Assert.assertEquals("Unexpected surName value", newOrder.getSurName(), result.getSurName());
    Assert.assertEquals("Unexpected createdDate value", newOrder.getCreatedDate(), result.getCreatedDate());
    Assert.assertEquals("Unexpected sum value", newOrder.getSum(), result.getSum());
  }

  @Test
  public void testSave_existEntity() { //TODO c++ naming style?
    String newSurName = "Bond";
    String newName = "James";
    BigInteger newSum = BigInteger.valueOf(1234L);
    Date newDate = new Date(1224455662);

    //TODO you now what I'm going to sey here
    Order order = new Order();
    order.setName("Alex");
    order.setSurName("Petrosyan");
    order.setCreatedDate(new Date());
    order.setSum(BigInteger.ZERO);
    Order existedOrder = entityManager.persist(order);
    entityManager.flush();

    existedOrder.setSurName(newSurName);
    existedOrder.setName(newName);
    existedOrder.setCreatedDate(newDate);
    existedOrder.setSum(newSum);

    Order result = repository.save(existedOrder);

    Assert.assertEquals("Id should not be changed", existedOrder.getId(), result.getId());
    Assert.assertEquals("Unexpected name value", newName, result.getName());
    Assert.assertEquals("Unexpected surName value", newSurName, result.getSurName());
    Assert.assertEquals("Unexpected createdDate value", newDate, result.getCreatedDate());
    Assert.assertEquals("Unexpected sum value", newSum, result.getSum());
  }

  @Test
  public void testFindById() {
    List<Order> orderList = prepareTestData().stream().map(entityManager::persist).collect(Collectors.toList());
    entityManager.flush();
    Order order = orderList.get(1);

    //TODO It seems like you are testing functionality of Spring repositories. I can assure you, everything is working fine in it.
    Order result = repository.findOne(order.getId());

    Assert.assertEquals("Unexpected order", order, result);
  }

  private Collection<Order> prepareTestData() {
    //TODO same stuff
    Order order1 = new Order();
    order1.setName("Vitalii");
    order1.setSurName("Pupkin");
    order1.setCreatedDate(new Date());
    order1.setSum(BigInteger.TEN);

    Order order2 = new Order();
    order2.setName("Alex");
    order2.setSurName("Bond");
    order2.setCreatedDate(new Date());
    order2.setSum(BigInteger.ONE);
    return Arrays.asList(order1, order2);
  }

}
