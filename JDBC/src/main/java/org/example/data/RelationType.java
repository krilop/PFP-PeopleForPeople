package org.example.data;

import lombok.Data;

@Data
public class RelationType {
    Long Id;
    String RelationTitle;

    public RelationType(Long id, String relationTitle) {
        Id = id;
        RelationTitle = relationTitle;
    }
}
