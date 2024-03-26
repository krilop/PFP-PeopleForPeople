package com.example.springhibernate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pair")
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
            inverseJoinColumns = @JoinColumn(name = "relation_type", referencedColumnName = "id"))
    private List<RelationType> relationTypes;

    public void addRelationTypes(RelationType relationType) {
        if(relationTypes == null||relationTypes.isEmpty())
            relationTypes = new ArrayList<>();
        relationTypes.add(relationType);
        relationType.getPairs().add(this);
    }

    public void removeRelationTypes(RelationType relationType) {
        if(relationTypes == null||relationTypes.isEmpty())
            return;
        relationTypes.remove(relationType);
        relationType.getPairs().remove(this);
    }
}
