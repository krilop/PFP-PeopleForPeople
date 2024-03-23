package com.example.springhibernate.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "relation_type")
public class RelationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "relation_title", unique = true, nullable = false)
    private String relationTitle;

    @Column
    @ManyToMany(mappedBy = "relationTypes")
    private List<Pair> pairs;
}
