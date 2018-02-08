package com.epam.bench.anpavlenko.entity;

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

//TODO not really sure why do you need PreOrder, entity which have 95% same fields as. How about just create one entity Order and constraint to table order_checks. You will make rediculus amount of data duplcation @see https://uk.wikipedia.org/wiki/Нормалізація_баз_даних up to 3rd form.
@Entity
@Table(name = "PRE_ORDERS")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PreOrder implements Serializable {
  private static final long serialVersionUID = -6999208313873299292L;

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  private String name;

  private String surName;

  @NotNull
  private Date createdDate;

  private BigInteger sum;
  @NotNull
  private boolean checked = false;

  public boolean isChecked() {
    return checked;
  }

  public void setChecked(boolean checked) {
    this.checked = checked;
  }

  public Long getId() {
    return id;
  }

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
