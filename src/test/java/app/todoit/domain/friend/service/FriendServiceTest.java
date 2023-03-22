//package app.todoit.domain.friend.service;
//
//import app.todoit.domain.auth.entity.User;
//import app.todoit.domain.auth.repository.UserRepository;
//import app.todoit.domain.friend.entity.PendingFriendEntity;
//import app.todoit.domain.friend.entity.PendingFriendId;
//import app.todoit.domain.friend.repository.PendingFriendRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
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
//    @Test
//    public void addFriend() {
//
//        friendService.addFriend(1L,2L);
//
//        PendingFriendEntity list = pendingFriendRepository.findById(new PendingFriendId(1L,2L)).get();
//        assertThat(list.getPendingFriendId().getUserId().equals(1L));
//        assertThat(list.getPendingFriendId().getFriendId().equals(2L));
//
//        friendService.addFriend(1L,2L);
//        boolean isNull = pendingFriendRepository.findById(new PendingFriendId(1L,2L)).isEmpty();
//        assertThat(isNull=true);
//    }
//
//}