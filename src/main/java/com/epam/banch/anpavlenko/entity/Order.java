package com.epam.banch.anpavlenko.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author an.pavlenko
 */

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Order implements Serializable {
  private static final long serialVersionUID = 2980590418208828166L;

  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  private String name;
  private String surName;
  @NotNull
  private Date createdDate;
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

  public void setCreatedDate(Date orderCreated) {
    this.createdDate = orderCreated;
  }

  public BigInteger getSum() {
    return sum;
  }

  public void setSum(BigInteger sum) {
    this.sum = sum;
  }
}
