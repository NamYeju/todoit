package app.todoit.domain.friend.api;

import app.todoit.domain.friend.entity.FriendEntity;
import app.todoit.domain.friend.entity.PendingFriendEntity;
import app.todoit.domain.friend.service.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FriendController {
    private final FriendService friendService;
    @PostMapping("/friend")
    public void addFriend (@RequestParam Long friendId) { //친구 신청
        friendService.addFriend(1L,friendId);
    }

    @GetMapping("/friend")
    public List<FriendEntity> getFriendList() {
        return friendService.getFriendsList();
    }

    @DeleteMapping("/friend")
    public void deleteFriend(@RequestParam Long friendId) { //친구 삭제
        friendService.deleteFriend(friendId);
    }

    @GetMapping("/friend/pending")
    public List<PendingFriendEntity> getPendingFriendList() { //신청 대기 목록 조회
        return friendService.getPendingFriends();
    }

    @PostMapping("/friend/accept")
    public void acceptFriend(@RequestParam Long friendId) { //친구 수락
        friendService.acceptFriend(1L,friendId);
    }
}
