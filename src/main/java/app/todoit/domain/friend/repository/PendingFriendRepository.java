package app.todoit.domain.friend.repository;

import app.todoit.domain.friend.entity.FriendEntity;
import app.todoit.domain.friend.entity.PendingFriendEntity;
import app.todoit.domain.friend.entity.PendingFriendId;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PendingFriendRepository extends JpaRepository<PendingFriendEntity, PendingFriendId> {
    List<PendingFriendEntity> findAllByPendingFriendIdFriendId(Long friendId);
    List<PendingFriendEntity> findAllByPendingFriendIdUserId(Long userId);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM PENDING_FRIENDS WHERE (user_id=?1 AND friend_id=?2) " +
            "OR (user_id=?2 AND friend_id=?1 ) ")
    Integer exists (@Param(value="userId") Long userId, @Param(value = "friendId") Long friendId);
}
