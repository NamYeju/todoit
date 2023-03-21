package app.todoit.domain.friend.entity;

import app.todoit.auth.entity.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pending_friends")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class PendingFriendEntity implements Serializable{

    @EmbeddedId
    PendingFriendId pendingFriendId;

    public PendingFriendEntity(Long userId, Long friendId) {
        this.pendingFriendId =  new PendingFriendId(userId, friendId);
    }
}
