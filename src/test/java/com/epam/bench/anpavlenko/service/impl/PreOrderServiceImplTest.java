package com.epam.bench.anpavlenko.service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.bench.anpavlenko.entity.PreOrder;
import com.epam.bench.anpavlenko.repository.PreOrderRepository;

/**
 * Unit test for {@link PreOrderServiceImpl}.
 *
 * @author an.pavlenko
 */
@RunWith(MockitoJUnitRunner.class)
public class PreOrderServiceImplTest {

  @Mock
  private PreOrderRepository repository;
  @InjectMocks
  private PreOrderServiceImpl service;

  @Test
  public void testGetUncheckedOrder() {

    service.getUncheckedOrder();

    Mockito.verify(repository).findByCheckedFalse();
  }

  @Test
  public void testSaveList() {
    List<PreOrder> preOrderList = Arrays.asList(new PreOrder(), new PreOrder());

    service.save(preOrderList);

    Mockito.verify(repository).save(preOrderList);
  }
}
