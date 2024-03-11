package org.example.data;

import lombok.Data;
@Data
public class Contact {
    Long id;
    Long user_id;
    Long contact_type;
    String info;

    public Contact(Long id, Long user_id, Long contact_type, String info) {
        this.id = id;
        this.user_id = user_id;
        this.contact_type = contact_type;
        this.info = info;
    }
}
