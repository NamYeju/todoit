package app.todoit.domain.friend.repository;

import app.todoit.domain.friend.entity.FriendEntity;
import app.todoit.domain.friend.entity.FriendId;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, FriendId> {
    @Query(nativeQuery = true,value = "SELECT * FROM FRIENDS WHERE user_id = ?1 OR friend_id = ?1")
    List<FriendEntity> findMyFriends (@Param(value = "userId") Long userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM FRIENDS WHERE (user_id=?1 AND friend_id=?2) OR " +
            "(user_id=?2 AND friend_id=?1) ")
    void deleteFriend (@Param(value = "userId") Long userId, @Param(value = "friendId") Long friendId);


    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM FRIENDS WHERE (user_id=?1 AND friend_id=?2) " +
            "OR (user_id=?2 AND friend_id=?1 ) ")
    Integer exists (@Param(value="userId") Long userId, @Param(value = "friendId") Long friendId);

    List<FriendEntity> findByUserId(Long userId);
    List<FriendEntity> findByFriendIdFriendId(Long friendId);
}
