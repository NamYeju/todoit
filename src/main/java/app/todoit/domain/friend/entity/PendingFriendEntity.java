package app.todoit.domain.friend.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "pending_friends")
@Getter
public class PendingFriendEntity {
    @EmbeddedId
    private PendingFriendsId pendingFriendsId;


    //생성자
    public PendingFriendEntity (Long userId, Long friendId) {
        this.pendingFriendsId.pendingFriendsIdBuilder(userId, friendId);
    }


}
