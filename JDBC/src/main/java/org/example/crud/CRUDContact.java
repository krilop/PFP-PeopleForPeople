package org.example.crud;

import lombok.Data;
@Data
public class CRUDContact {
    Long id;
    Long user_id;
    Long contact_type;
    String info;

    public CRUDContact(Long id, Long user_id, Long contact_type, String info) {
        this.id = id;
        this.user_id = user_id;
        this.contact_type = contact_type;
        this.info = info;
    }
}
