package app.todoit.domain.friend.api;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.friend.dto.FriendResponseDto;
import app.todoit.domain.friend.dto.PendingFriendResponseDto;
import app.todoit.domain.friend.entity.FriendEntity;
import app.todoit.domain.friend.entity.PendingFriendEntity;
import app.todoit.domain.friend.service.FriendService;
import app.todoit.global.interceptor.UserThreadLocal;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FriendController {
    private final FriendService friendService;
    @PostMapping("/friend")
    public String addFriend (@RequestParam Long friendId) { //친구 신청
        User user = UserThreadLocal.get();
        return friendService.addFriend(user,friendId);
    }

    @GetMapping("/friend")
    public FriendResponseDto getFriendList() {
        User user = UserThreadLocal.get();
        return friendService.getFriendsList(user);
    }

    @DeleteMapping("/friend")
    public String deleteFriend(@RequestParam Long friendId) { //친구 삭제
        User user = UserThreadLocal.get();
        return friendService.deleteFriend(user,friendId);
    }

    @GetMapping("/friend/pending")
    public PendingFriendResponseDto getPendingFriendList() { //신청 대기 목록 조회
        User user = UserThreadLocal.get();
        return friendService.getPendingFriends(user);
    }

    @PostMapping("/friend/accept")
    public String acceptFriend(@RequestParam Long friendId) { //친구 수락
        User user = UserThreadLocal.get();
        return friendService.acceptFriend(user,friendId);
    }
}
