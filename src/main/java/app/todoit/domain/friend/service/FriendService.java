package app.todoit.domain.friend.service;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.auth.repository.UserRepository;
import app.todoit.domain.friend.dto.FriendResponseDto;
import app.todoit.domain.friend.dto.PendingFriendResponseDto;
import app.todoit.domain.friend.entity.FriendEntity;
import app.todoit.domain.friend.entity.FriendId;
import app.todoit.domain.friend.entity.PendingFriendEntity;
import app.todoit.domain.friend.entity.PendingFriendId;
import app.todoit.domain.friend.exception.FriendException;
import app.todoit.domain.friend.repository.FriendRepository;
import app.todoit.domain.friend.repository.PendingFriendRepository;
import app.todoit.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FriendService {
    private final PendingFriendRepository pendingFriendRepository;
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    public String addFriend (User user, Long friendId) {
        //친구 신청 & 취소
        User friend= getUserEntity(friendId);

        PendingFriendEntity p = new PendingFriendEntity(user, friend);
        if (friendRepository.existsById(new FriendId(user.getId(), friendId)) || friendRepository.existsById(new FriendId(friendId,user.getId()))) {
            throw new FriendException(ErrorCode.ALREADY_FRIENDS);
        }
        else if (pendingFriendRepository.existsById(p.getPendingFriendId())) { //이미 신청한 상태면 신청 취소
            pendingFriendRepository.deleteById(p.getPendingFriendId());
            return "FRIEND CANCEL SUCCESS";
        }
        else {
            pendingFriendRepository.save(p);
            return "FRIEND ADD SUCCESS";
        }
    }

    public String acceptFriend (User user, Long friendId) {
        //pending Friend 와 Friend 의 user_id, friend_id 방향 같게 저장
        //친구 수락 (pendingFriends 에서 삭제하고 friend에 추가)
        User friend= getUserEntity(friendId);
        pendingFriendRepository.deleteById(new PendingFriendId(friendId,user.getId()));
        friendRepository.save(new FriendEntity(friend, user));

        return "ACCEPT SUCCESS";

    }

    public String deleteFriend (User user, Long friendId) {
        //친구 삭제
        friendRepository.deleteFriend(user.getId(), friendId); //예외처리 해야함
        return "DELETE SUCCESS";
    }

    public PendingFriendResponseDto getPendingFriends (User user) {
        //수락 대기 목록 조회
        PendingFriendResponseDto res = new PendingFriendResponseDto();
        res.entityToDto( pendingFriendRepository.findAllByPendingFriendIdFriendId(user.getId()));
        return res;

    }

    public FriendResponseDto getFriendsList (User user) {
        //친구 목록 조회 (양방향인 경우)
        FriendResponseDto res = new FriendResponseDto();

        res.entityToDtoByMe(friendRepository.findByUserId(user.getId())); //내가 건 친구
        res.entityToDtoByFriend(friendRepository.findByFriendIdFriendId(user.getId())); //내가 수락한 친구

        return res;


    }

    public User getUserEntity(Long userId) {
        User user = userRepository.findById(userId).get();
        return user;
    }
}
