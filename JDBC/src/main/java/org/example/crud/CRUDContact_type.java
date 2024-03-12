package org.example.crud;

import lombok.Data;
@Data
public class CRUDContact_type {
    Long id;
    String contact_title;

    public CRUDContact_type(Long id, String contact_title) {
        this.id = id;
        this.contact_title = contact_title;
    }
}
