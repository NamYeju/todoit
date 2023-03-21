package app.todoit.domain.friend.entity;

import app.todoit.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "friends")
@Getter
public class FriendEntity{

    @MapsId("userId") // friendId.userId에 매핑
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("friendId") // friendId.friendId에 매핑
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    @EmbeddedId
    private FriendId friendId;

    public FriendEntity(User user, User friend) {
        this.user=user;
        this.friend= friend;
        this.friendId = new FriendId(user.getId(),friend.getId());
    }
}
