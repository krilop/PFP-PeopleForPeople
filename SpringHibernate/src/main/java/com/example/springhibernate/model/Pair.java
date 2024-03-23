package com.example.springhibernate.model;

import jakarta.persistence.*;

import java.util.List;

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

    @Column(name = "relation_title",nullable = false)
    @ManyToMany
    @JoinTable(
            name = "relation_type",
            joinColumns = @JoinColumn(name = "pair_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "relation_type_id", referencedColumnName = "id"))
    private List<RelationType> relationTypes;
}
