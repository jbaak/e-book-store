package com.jbaak.model.entity;

import com.jbaak.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customers")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "first_name", nullable = false )
    private String firtsName;

    @Column( name = "last_name", nullable = false )
    private String lastName;

    @Column( name = "full_name", nullable = false )
    private String fullName;

    private String email;

    private String password;

    @Column( name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column( name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
