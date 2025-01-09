package com.jbaak.repository;

import com.jbaak.model.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {
    //Método para encontrar las coleciones por usuario
    List<Collection> findByCustomerId(Integer customerId);
}
