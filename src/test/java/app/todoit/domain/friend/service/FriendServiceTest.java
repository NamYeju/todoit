//package app.todoit.domain.friend.service;
//
//import app.todoit.auth.entity.User;
//import app.todoit.auth.repository.UserRepository;
//import app.todoit.domain.friend.entity.FriendEntity;
//import app.todoit.domain.friend.entity.FriendId;
//import app.todoit.domain.friend.entity.PendingFriendEntity;
//import app.todoit.domain.friend.entity.PendingFriendId;
//import app.todoit.domain.friend.repository.FriendRepository;
//import app.todoit.domain.friend.repository.PendingFriendRepository;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//class FriendServiceTest {
//
//    @Autowired
//    FriendService friendService;
//    @Autowired
//    PendingFriendRepository pendingFriendRepository;
//
//    @Autowired
//    FriendRepository friendRepository;
//
//
//    @Test
//    @DisplayName("친구 신청 및 취소")
//    public void addFriend() {
//        friendService.addFriend(1L,2L);
//        PendingFriendEntity list = pendingFriendRepository.findById(new PendingFriendId(1L,2L)).get();
//        assertThat(list.getPendingFriendId().getUserId().equals(1L));
//        assertThat(list.getPendingFriendId().getFriendId().equals(2L));
//
//        friendService.addFriend(1L,2L);
//        boolean isNull = pendingFriendRepository.findById(new PendingFriendId(1L,2L)).isEmpty();
//        assertThat(isNull=true);
//    }
//
//    @Test
//    @DisplayName("친구 수락")
//    public void acceptFriend() {
//        friendService.addFriend(1L,2L);
//        friendService.addFriend(1L,2L); //1번이 2번에게 친구신청
//        friendService.acceptFriend(2L,1L); //2번이 수락하는 주체
//
//        Optional<PendingFriendEntity> pendingFriend = pendingFriendRepository.findById(new PendingFriendId(1L, 2L));
//        assertThat(pendingFriend).isEmpty();
//        Optional<FriendEntity> friend = friendRepository.findById(new FriendId(1L, 2L));
//        assertThat(friend.isPresent());
//
//    }
//
//    @Test
//    @DisplayName("친구 삭제")
//    public void deleteFriend() {
//        friendService.addFriend(1L,2L);
//        friendService.acceptFriend(2L,1L);
//        friendService.deleteFriend(1l, 2L);
//        Optional<FriendEntity> byId = friendRepository.findById(new FriendId(1L, 2L));
//        assertThat(byId.isEmpty());
//
//        friendService.addFriend(1L,2L);
//        friendService.acceptFriend(2L,1L);
//        friendService.deleteFriend(2L,1L);
//        Optional<FriendEntity> byId2 = friendRepository.findById(new FriendId(1L, 2L));
//        assertThat(byId2.isEmpty());
//
//    }
//
//
//}