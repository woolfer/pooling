package com.epam.bench.anpavlenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.bench.anpavlenko.entity.Order;

/**
 * @author an.pavlenko
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
