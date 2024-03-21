package com.example.springhibernate.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "authorization")
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(name = "hash_of_pass", nullable = false)
    private String hashOfPass;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(mappedBy = "user_data",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserData userData;
}
