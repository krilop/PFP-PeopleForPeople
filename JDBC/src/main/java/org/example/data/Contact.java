package org.example.data;

import lombok.Data;
@Data
public class Contact {
    Long id;
    Long userId;
    Long contactType;
    String info;

    public Contact(Long id, Long userId, Long contactType, String info) {
        this.id = id;
        this.userId = userId;
        this.contactType = contactType;
        this.info = info;
    }
}
