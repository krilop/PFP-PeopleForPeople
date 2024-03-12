package org.example.data;

import lombok.Data;
@Data
public class Relation_type {
    Long id;
    String relation_title;

    public Relation_type(Long id, String relation_title) {
        this.id = id;
        this.relation_title = relation_title;
    }
}
