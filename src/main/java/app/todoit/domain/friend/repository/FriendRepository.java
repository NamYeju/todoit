package app.todoit.domain.friend.repository;

import app.todoit.domain.friend.entity.FriendEntity;
import app.todoit.domain.friend.entity.FriendId;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, FriendId> {
    @Query(nativeQuery = true,value = "SELECT * FROM FRIENDS WHERE user_id = ?1 OR friend_id = ?1")
    List<FriendEntity> findMyFriends (@Param(value = "userId") Long userId);

    @Query(nativeQuery = true, value = "DELETE FROM FRIENDS WHERE (user_id=?1 AND friend_id=?2) OR " +
            "(user_id=?2 AND friend_id=?1) ")
    Integer deleteFriend (@Param(value = "userId") Long userId, @Param(value = "friendId") Long friendId);
}
