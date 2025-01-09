package com.jbaak.repository;

import com.jbaak.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,  Integer> {
    List<Purchase> findByCustomerId(Integer customerId);
}
