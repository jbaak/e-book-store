package com.jbaak.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false )
    private String name;

    @Column( name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column( name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id",
            foreignKey = @ForeignKey (name= "FK_collection_users"))
    private User customer;

    @JsonIgnore
    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private List<CollectionBook> collectionBooks;
}
