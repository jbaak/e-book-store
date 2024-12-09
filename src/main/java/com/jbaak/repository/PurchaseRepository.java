package com.jbaak.repository;

import com.jbaak.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,  Integer> {
}
