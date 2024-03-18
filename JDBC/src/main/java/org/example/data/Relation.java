package org.example.data;

import lombok.Data;

@Data
public class Relation {

    Long Id;
    Long UserId;
    Long FriendId;
    Long RelationType;

    public Relation(Long id, Long userId, Long friendId, Long relationType) {
        Id = id;
        UserId = userId;
        FriendId = friendId;
        RelationType = relationType;
    }
}
