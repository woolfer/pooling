package com.epam.banch.anpavlenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.banch.anpavlenko.entity.Order;

/**
 * @author an.pavlenko
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
