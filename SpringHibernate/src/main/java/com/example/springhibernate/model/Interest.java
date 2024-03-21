package com.example.springhibernate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "interest")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "title_of_type")
    private String titleOfType;

    @Column(name = "title_of_type", unique = true)
    @ManyToMany(mappedBy = "interests")
    private List<UserData> userDataList;
}
