package org.example.data;

import lombok.Data;
@Data
public class Contact_type {
    Long id;
    String contact_title;

    public Contact_type(Long id, String contact_title) {
        this.id = id;
        this.contact_title = contact_title;
    }
}
