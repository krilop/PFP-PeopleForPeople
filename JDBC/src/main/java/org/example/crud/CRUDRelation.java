package org.example.crud;

import lombok.Data;
@Data
public class CRUDRelation {

    Long id;
    Long user_id;
    Long friend_id;
    Long relation_type;

    public CRUDRelation(Long id, Long user_id, Long friend_id, Long relation_type) {
        this.id = id;
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.relation_type = relation_type;
    }
}
