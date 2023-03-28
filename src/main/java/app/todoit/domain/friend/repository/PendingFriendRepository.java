package app.todoit.domain.friend.repository;

import app.todoit.domain.friend.entity.FriendEntity;
import app.todoit.domain.friend.entity.PendingFriendEntity;
import app.todoit.domain.friend.entity.PendingFriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PendingFriendRepository extends JpaRepository<PendingFriendEntity, PendingFriendId> {
    List<PendingFriendEntity> findAllByPendingFriendIdFriendId(Long friendId);
    List<PendingFriendEntity> findAllByPendingFriendIdUserId(Long userId);
}
