package com.example.springhibernate.DTO;

import com.example.springhibernate.model.ContactType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDTO {

    Long id;

    String info;

    ContactType contactType;
}
