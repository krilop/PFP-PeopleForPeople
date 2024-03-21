package com.example.springhibernate.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column
    private String discription;

    @Column(name="date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private boolean gender;

    @Column
    @Transient
    private short age;


    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<Long, List<String>> media = new HashMap<>();

    @ManyToMany
    @JoinTable(
            name = "interest_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    private List<Interest> interests;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Contact> contacts;

}
