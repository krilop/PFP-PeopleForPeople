package org.example.data;

import lombok.Data;

@Data
public class RelationType {
    Long id;
    String relationTitle;

    public RelationType(Long id, String relationTitle) {
        this.id = id;
        this.relationTitle = relationTitle;
    }
}
