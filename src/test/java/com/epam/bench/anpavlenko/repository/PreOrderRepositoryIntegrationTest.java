package com.epam.bench.anpavlenko.repository;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.epam.bench.anpavlenko.Application;
import com.epam.bench.anpavlenko.entity.PreOrder;

/**
 * Integration test for {@link PreOrderRepository}.
 *
 * @author an.pavlenko
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = Application.class)
public class PreOrderRepositoryIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private PreOrderRepository repository;

  @Test
  public void testFindByCheckedFalse() {
    PreOrder order1 = new PreOrder();
    order1.setName("Vitalii");
    order1.setSurName("Pupkin");
    order1.setCreatedDate(new Date());
    order1.setSum(BigInteger.TEN);
    order1.setChecked(true);

    PreOrder order2 = new PreOrder();
    order2.setName("Alex");
    order2.setSurName("Bond");
    order2.setCreatedDate(new Date());
    order2.setSum(BigInteger.ONE);

    Arrays.asList(order1, order2).forEach(entityManager::persist);
    entityManager.flush();

    List<PreOrder> preOrderList = repository.findByCheckedFalse();

    Assert.assertEquals("Unexpected value", 1, preOrderList.size());
    Assert.assertEquals("Unexpected founded preOrder", order2, preOrderList.get(0));
  }

}
