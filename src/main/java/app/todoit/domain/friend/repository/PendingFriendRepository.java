package app.todoit.domain.friend.repository;

import app.todoit.domain.friend.entity.PendingFriendEntity;
import app.todoit.domain.friend.entity.PendingFriendsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingFriendRepository extends JpaRepository<PendingFriendEntity, PendingFriendsId> {
}
