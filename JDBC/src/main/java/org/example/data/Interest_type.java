package org.example.data;

import lombok.Data;
@Data
public class Interest_type {
    Long id;
    String interest_title;

    public Interest_type(Long id, String interest_title) {
        this.id = id;
        this.interest_title = interest_title;
    }
}
