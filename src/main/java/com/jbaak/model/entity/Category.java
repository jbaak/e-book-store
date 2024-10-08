package com.jbaak.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "name", nullable = false, length = 50 )
    private String name;
    @Column( name = "description", columnDefinition = "TEXT")
    private String description;
    @Column( name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column( name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
