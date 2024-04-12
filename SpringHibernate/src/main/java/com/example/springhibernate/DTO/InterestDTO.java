package com.example.springhibernate.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterestDTO {

    private Long id;

    private String titleOfType;

    private String icon;

}