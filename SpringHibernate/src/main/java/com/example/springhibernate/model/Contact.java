package com.example.springhibernate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String info;

    @ManyToOne( cascade = {
            CascadeType.MERGE, CascadeType.PERSIST
    })
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserData userData;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type", nullable = false)
    private ContactType contactType;
}
