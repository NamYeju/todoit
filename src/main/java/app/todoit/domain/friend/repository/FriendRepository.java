package app.todoit.domain.friend.repository;

import app.todoit.domain.friend.entity.FriendEntity;
import app.todoit.domain.friend.entity.FriendsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, FriendsId> {

}
