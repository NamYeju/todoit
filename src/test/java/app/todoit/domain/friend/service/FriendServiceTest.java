//package app.todoit.domain.friend.service;
//
//import app.todoit.domain.auth.entity.User;
//import app.todoit.domain.auth.repository.UserRepository;
//import app.todoit.domain.friend.entity.FriendEntity;
//import app.todoit.domain.friend.entity.FriendId;
//import app.todoit.domain.friend.entity.PendingFriendEntity;
//import app.todoit.domain.friend.entity.PendingFriendId;
//import app.todoit.domain.friend.exception.FriendException;
//import app.todoit.domain.friend.repository.FriendRepository;
//import app.todoit.domain.friend.repository.PendingFriendRepository;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.event.annotation.BeforeTestClass;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
//    @Autowired
//    UserRepository userRepository;
//
//
//
//    @Test
//    @DisplayName("친구 신청")
//    @Order(1)
//    public void addFriend() {
//        //given
//        User user = userRepository.findById(1L).get();
//
//        //when (1)
//        friendService.addFriend(user,2L);
//        Optional<PendingFriendEntity> pendingFriend = pendingFriendRepository.findById(new PendingFriendId(1L, 2L));
//
//        //then (1)
//        assertTrue(pendingFriend.isPresent());
//    }
//
//    @Test
//    @DisplayName("친구 수락")
//    @Order(2)
//    public void acceptFriend() {
//        //given
//        User user = userRepository.findById(2L).get();
//
//        //when
//        friendService.acceptFriend(user,1L); //2번이 수락하는 주체
//
//        //then
//        Optional<PendingFriendEntity> pendingFriend = pendingFriendRepository.findById(new PendingFriendId(1L, 2L));
//        assertTrue(pendingFriend.isEmpty());
//        Optional<FriendEntity> friend = friendRepository.findById(new FriendId(1L, 2L));
//        assertTrue(friend.isPresent());
//    }
//
//    @Test
//    @DisplayName("친구 삭제")
//    @Order(3)
//    public void deleteFriend() {
//        //given
//        User user = userRepository.findById(1L).get();
//
//        //when
//        friendService.deleteFriend(user, 2L);
//
//        //then
//        Optional<FriendEntity> idDeleted = friendRepository.findById(new FriendId(1L, 2L));
//        assertTrue(idDeleted.isEmpty());
//
//
//    }
//
//    @Test
//    @DisplayName("친구 신청 실패(이미 친구인 경우)")
//    @Order(4)
//    public void alreadyFriendException() {
//        //given
//        User user = userRepository.findById(1L).get();
//        User friend = userRepository.findById(2L).get();
//        friendRepository.save(new FriendEntity(user, friend));
//
//        //when
//
//        //then
//        assertThrows(FriendException.class, () -> {
//            friendService.addFriend(user,friend.getId());
//        });
//    }
//
//
//
//}