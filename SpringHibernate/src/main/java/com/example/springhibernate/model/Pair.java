package com.example.springhibernate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "relation")
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE
    })
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",nullable = false)
    private UserData userId;

    @ManyToOne(cascade = {
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE
    })
    @JoinColumn(name = "another_id", referencedColumnName = "user_id",nullable = false)
    private UserData anotherId;

    @Column(nullable = false)
    private Boolean direction;

    @ManyToMany(cascade = {
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE
    })
    @JoinTable(
            name = "relation",
            joinColumns = @JoinColumn(name = "pair_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "relation_type_id", referencedColumnName = "id"))
    private List<RelationType> relationTypes;
}
