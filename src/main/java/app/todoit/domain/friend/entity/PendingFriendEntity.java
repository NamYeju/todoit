package app.todoit.domain.friend.entity;

import app.todoit.auth.entity.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pending_friends")
@Getter
@NoArgsConstructor
public class PendingFriendEntity implements Serializable{

    @MapsId("user_id")
    @ManyToOne
    private User user;

    @MapsId("friend_id")
    @ManyToOne
    private User friend;

    @EmbeddedId
    PendingFriendId pendingFriendId;

    public PendingFriendEntity(User user, User friend) {
        this.user=user;
        this.friend= friend;
        this.pendingFriendId =  new PendingFriendId(user.getId(), friend.getId());
    }
}
