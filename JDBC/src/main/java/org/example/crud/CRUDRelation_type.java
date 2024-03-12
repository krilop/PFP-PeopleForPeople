package org.example.crud;

import lombok.Data;
@Data
public class CRUDRelation_type {
    Long id;
    String relation_title;

    public CRUDRelation_type(Long id, String relation_title) {
        this.id = id;
        this.relation_title = relation_title;
    }
}
