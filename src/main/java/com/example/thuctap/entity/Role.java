package com.example.thuctap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,length =15)
    private String name; //[ADMIN] , [MEMBER]
}
