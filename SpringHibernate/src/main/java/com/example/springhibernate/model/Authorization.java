package com.example.springhibernate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"authorization\"")
@Getter
@Setter
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @Column(name = "hash_of_pass", nullable = false)
    private String hashOfPass;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE
    })
    @JsonIgnore
    @PrimaryKeyJoinColumn(name = "user_data")
    private UserData userData;

    // Constructors, getters, and setters omitted for brevity
}
