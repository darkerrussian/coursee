package com.example.coursee.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "roles")


public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "role")
    private String name;


}
