package org.example.crud;

import lombok.Data;

@Data
public class CRUDInterest_type_user {

    Long id;
    Long user_id;
    Long interest_id;

    public CRUDInterest_type_user(Long id, Long user_id, Long interest_id) {
        this.id = id;
        this.user_id = user_id;
        this.interest_id = interest_id;
    }
}
