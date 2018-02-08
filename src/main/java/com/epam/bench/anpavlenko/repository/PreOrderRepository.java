package com.epam.bench.anpavlenko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.bench.anpavlenko.entity.PreOrder;

/**
 * @author an.pavlenko
 */
//TODO see implementation comentsIf you will remove preorder entity it will also become useless
@Repository
public interface PreOrderRepository extends JpaRepository<PreOrder, Long> {

  List<PreOrder> findByCheckedFalse();
}
