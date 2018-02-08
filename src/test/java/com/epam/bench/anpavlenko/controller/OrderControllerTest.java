package com.epam.bench.anpavlenko.controller;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epam.bench.anpavlenko.Application;
import com.epam.bench.anpavlenko.entity.Order;
import com.epam.bench.anpavlenko.service.OrderService;

/**
 * Unit test for {@link OrderController}.
 *
 * @author an.pavlenko
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
@SpringBootTest(classes = Application.class)
public class OrderControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private OrderService service;

  @Test
  public void testGetAllOrders() throws Exception {

    Order testData = getTestData();

    Mockito.when(service.getAll()).thenReturn(Arrays.asList(testData));

    mvc.perform(MockMvcRequestBuilders.get("/orders")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(testData.getName())));
  }
//TODO new Order? not again
  private Order getTestData() {
    Order order = new Order();
    order.setName("Vitalii");
    order.setSurName("Pupkin");
    order.setCreatedDate(new Date());
    order.setSum(BigInteger.TEN);

    return order;
  }
}
