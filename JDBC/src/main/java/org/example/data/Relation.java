package org.example.data;

import lombok.Data;

@Data
public class Relation {

    Long id;
    Long userId;
    Long friendId;
    Long relationType;

    public Relation(Long id, Long userId, Long friendId, Long relationType) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.relationType = relationType;
    }
}
