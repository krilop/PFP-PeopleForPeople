package com.example.springhibernate.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String name; //necessary

    private String lastName;

    private String description;

    private LocalDate dateOfBirth; //necessary

    private boolean gender;  //necessary

    private String media;

}
