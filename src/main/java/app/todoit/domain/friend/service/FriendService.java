package app.todoit.domain.friend.service;

import app.todoit.auth.entity.User;
import app.todoit.auth.repository.UserRepository;
import app.todoit.domain.friend.entity.FriendEntity;
import app.todoit.domain.friend.entity.PendingFriendEntity;
import app.todoit.domain.friend.entity.PendingFriendId;
import app.todoit.domain.friend.repository.FriendRepository;
import app.todoit.domain.friend.repository.PendingFriendRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendService {
    private final PendingFriendRepository pendingFriendRepository;
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    public void addFriend (Long userId, Long friendId) {
        //친구 신청 & 취소
        PendingFriendEntity p = new PendingFriendEntity(userId, friendId);
        if (pendingFriendRepository.existsById(p.getPendingFriendId())) { //이미 신청한 상태면
            //취소
            pendingFriendRepository.deleteById(p.getPendingFriendId());
        }
        else {
            pendingFriendRepository.save(p);
        }
    }

    public void acceptFriend (Long userId, Long friendId) {
        userId = 2L;
        friendId=1L;
      pendingFriendRepository.deleteById(new PendingFriendId(friendId,userId));
      //pending Friend 와 Friend의 user_id, friend_id 의방향 같게 저장
      friendRepository.save(new FriendEntity(friendId, userId));
        //친구 수락 (pendingFriends 에서 삭제하고 friend에 추가)

    }

    public void deleteFriend (Long friendId) {
        //친구 삭제
       // new FriendsId
        //FriendRepository.deleteById(FriendsId);
    }

    public List<PendingFriendEntity> getPendingFriends () {
        //수락 대기 목록 조회
        Long userId=2L;
        return pendingFriendRepository.findAllByPendingFriendIdFriendId(userId);
    }

    public List<FriendEntity> getFriendsList () {
        //친구 목록 조회 (양방향인 경우)
        Long userId = 1L;
        return friendRepository.findMyFriends(userId);

    }

    public User getUserEntity(Long userId) {
        User user = userRepository.findById(userId).get();
        return user;
    }
}
