package com.epam.bench.anpavlenko.converter;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.epam.bench.anpavlenko.entity.Order;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

/**
 * @author an.pavlenko
 */
@Service("orderConverter")
public class OrderConverterImpl implements POJOConverter<Order, OrderConverterImpl.OrderPOJO> {

  @Override
  public Collection<Order> convert(Collection<OrderPOJO> pojos) {
    return pojos.stream().map(this::convertPOJO).collect(Collectors.toList());
  }

  @Override
  public Order convertPOJO(OrderPOJO pojo) {
    Order order = new Order();
    order.setName(pojo.getName());
    order.setSurName(pojo.getSurName());
    order.setCreatedDate(pojo.getCreatedDate());
    order.setSum(pojo.getSum());
    return order;
  }

  public static class OrderPOJO {
    @CsvBindByName(required = true)
    private String name;
    @CsvBindByName
    private String surName;
    @CsvBindByName(required = true)
    @CsvDate
    private Date createdDate;
    @CsvBindByName
    private BigInteger sum;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getSurName() {
      return surName;
    }

    public void setSurName(String surName) {
      this.surName = surName;
    }

    public Date getCreatedDate() {
      return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
      this.createdDate = createdDate;
    }

    public BigInteger getSum() {
      return sum;
    }

    public void setSum(BigInteger sum) {
      this.sum = sum;
    }
  }
}
