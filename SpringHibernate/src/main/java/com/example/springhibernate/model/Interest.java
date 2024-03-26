package com.example.springhibernate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "interest")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "title_of_type")
    private String titleOfType;

    @Column(name="icon")
    private String Icon;

    @JsonIgnore
    @ManyToMany(mappedBy = "interests", cascade = {
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE
    })
    private List<UserData> userDataList;
}
