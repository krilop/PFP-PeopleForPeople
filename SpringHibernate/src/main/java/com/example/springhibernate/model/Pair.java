package com.example.springhibernate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "relation")
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",nullable = false)
    private UserData userId;

    @ManyToOne
    @JoinColumn(name = "another_id", referencedColumnName = "user_id",nullable = false)
    private UserData anotherId;

    @Column(nullable = false)
    private Boolean direction;

    @Column(name = "relation_type",nullable = false)
    @ManyToMany
    @JoinTable(
            name = "relation_type",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<RelationType> relationTypes;
}
