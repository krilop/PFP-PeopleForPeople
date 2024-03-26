package com.example.springhibernate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_data")
public class UserData {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String description;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private int gender;

    @Column
    @Transient
    private int age;

    @Column
    private String media;

    @JsonIgnore
    @ManyToMany(cascade = {
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE
    })
    @JoinTable(
            name = "interest_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    private List<Interest> interests;

    @JsonIgnore
    @OneToMany(cascade = {
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE
    })
    @JoinColumn(name = "user_id")
    private List<Contact> contacts;

    @JsonIgnore
    @OneToOne(cascade = {
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE
    })
    @PrimaryKeyJoinColumn
    private Authorization authorization;


    public void addInterest(Interest interest) {
        if(interests == null||interests.isEmpty())
            interests = new ArrayList<>();
        interests.add(interest);
        interest.getUserDataList().add(this);
    }

    // Метод для удаления интереса у пользователя (если необходимо)
    public void removeInterest(Interest interest) {
        if(interests == null||interests.isEmpty())
            return;
        interests.remove(interest);
        interest.getUserDataList().remove(this);
    }

}
