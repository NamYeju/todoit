package app.todoit.domain.friend.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
@Getter
@Setter
public class PendingFriendsId implements Serializable {

    @Column(name="user_id")
    private Long userId;

    @Column(name="friend_id")
    private Long friendId;

    @Builder
    public PendingFriendsId pendingFriendsIdBuilder (Long userId, Long friendId) {
        this.userId=userId;
        this.friendId=friendId;
        return this;
    }

}
