package app.todoit.domain.friend.entity;

import lombok.AllArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Entity
@Table(name = "friends")
public class FriendEntity {

    @EmbeddedId
    private FriendsId friendsId;

}
