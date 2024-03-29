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

    private String name;

    private String lastName;

    private String description;

    private LocalDate dateOfBirth;

    private boolean gender;

    private String media;

}
