package app.todoit.domain.friend.api;

import app.todoit.domain.friend.service.FriendService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class FriendController {
    private final FriendService friendService;
    @PostMapping("/friend")
    public void addFriend (@PathVariable Long friendId) { //친구 신청
        friendService.addFriend(friendId);
    }

    @GetMapping("/friend")
    public void getFriendList() {
        friendService.getFriendsList();
    }

    @DeleteMapping("/friend")
    public void deleteFriend(@PathVariable Long friendId) { //친구 삭제
        friendService.deleteFriend(friendId);
    }

    @GetMapping("/friend/pending")
    public void getPendingFriendList() { //신청 대기 목록 조회
        friendService.getPendingFriends();
    }

    @PostMapping("/friend/accept")
    public void acceptFriend(@PathVariable Long friendId) { //친구 수락
        friendService.acceptFriend(friendId);
    }
}
