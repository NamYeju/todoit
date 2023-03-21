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
@EqualsAndHashCode
@Getter
public class FriendEntity{
   @EmbeddedId
    private FriendId friendId;

    public FriendEntity(Long userId, Long friendId) {
        this.friendId = new FriendId(userId,friendId);
    }
}
