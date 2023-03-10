package app.todoit.domain.friend.service;

import app.todoit.domain.friend.entity.PendingFriendEntity;
import app.todoit.domain.friend.entity.PendingFriendsId;
import app.todoit.domain.friend.repository.FriendRepository;
import app.todoit.domain.friend.repository.PendingFriendRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FriendService {
    private final PendingFriendRepository pendingFriendRepository;
    private final FriendRepository friendRepository;
    public void addFriend (Long friendId) {
        //친구 신청 & 취소
        Long userId = 1L;
        PendingFriendEntity p = new PendingFriendEntity(userId, friendId);
       // pendingFriendRepository.save(p);
    }

    public void acceptFriend (Long friendId) {
        //친구 수락
    }

    public void deleteFriend (Long friendId) {
        //친구 삭제
      // new FriendsId
        //FriendRepository.deleteById(FriendsId);
    }

    public void getPendingFriends () {
        //수락 대기 목록 조회
        //pendingFriendRepository.findAll();
    }

    public void getFriendsList () {
        //친구 목록 조회 (양방향인 경우)
       // friendRepository.findAll();
    }
}
