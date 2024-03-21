package com.example.springhibernate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "relation_type")
public class RelationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "relation_title", unique = true, nullable = false)
    private String relationTitle;

    @Column
    @ManyToMany(mappedBy = "pair")
    private List<Pair> pairs;
}
