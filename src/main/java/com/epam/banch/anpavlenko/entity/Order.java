package com.epam.banch.anpavlenko.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter @Setter
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
  private Date orderCreated;
  private BigInteger sum;
}
