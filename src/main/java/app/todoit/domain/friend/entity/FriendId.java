package app.todoit.domain.friend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MapsId;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FriendId implements Serializable {

    @Column(name = "user_id")
    private Long userId; //@MapsId("userId") 매핑
    @Column(name = "friend_id")
    private Long friendId; //@MapsId("friendId") 매핑


}
