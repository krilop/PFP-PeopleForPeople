package com.example.springhibernate.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationDTO {

    String userName;
    String anotherName;
    String relationTitle;
}
