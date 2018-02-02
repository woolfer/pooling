package com.epam.bench.anpavlenko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.bench.anpavlenko.entity.PreOrder;

/**
 * @author an.pavlenko
 */
@Repository
public interface PreOrderRepository extends JpaRepository<PreOrder, Long> {

  List<PreOrder> findByCheckedFalse();
}
