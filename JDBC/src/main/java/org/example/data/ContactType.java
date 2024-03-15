package org.example.data;

import lombok.Data;

@Data
public class ContactType {
    Long id;
    String contactTitle;

    public ContactType(Long id, String contactTitle) {
        this.id = id;
        this.contactTitle = contactTitle;
    }
}
