package org.example.crud;

import lombok.Data;
@Data
public class CRUDInterest_type {
    Long id;
    String interest_title;

    public CRUDInterest_type(Long id, String interest_title) {
        this.id = id;
        this.interest_title = interest_title;
    }
}
