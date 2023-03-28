package app.todoit.domain.friend.api;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.friend.dto.FriendResponseDto;
import app.todoit.domain.friend.dto.JoinCheckDto;
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
@RequestMapping("/api/friend")
public class FriendController {
    private final FriendService friendService;
    @PostMapping("/")
    public String addFriend (@RequestParam Long friendId) { //친구 신청
        User user = UserThreadLocal.get();
        return friendService.addFriend(user,friendId);
    }

    @GetMapping("/")
    public FriendResponseDto getFriendList() { //양방향 친구 목록
        User user = UserThreadLocal.get();
        return friendService.getFriendsList(user);
    }

    @DeleteMapping("/")
    public String deleteFriend(@RequestParam Long friendId) { //친구 삭제 (누가하든 상관없음)
        User user = UserThreadLocal.get();
        return friendService.deleteFriend(user,friendId);
    }

    @GetMapping("/pending")
    public PendingFriendResponseDto getPendingFriendList() { //내가 친구 신청힌 목록 조회 (상대는 아직 수락x)
        User user = UserThreadLocal.get();
        return friendService.getPendingFriends(user);
    }

    @PostMapping("/accept")
    public String acceptFriend(@RequestParam Long friendId) { //친구 수락
        User user = UserThreadLocal.get();
        return friendService.acceptFriend(user,friendId);
    }

    @PostMapping("/join")
    public JoinCheckDto checkJoin (@RequestBody List<String> phone) { //핸드폰 번호로 가입한 사용자인지 확인
        return friendService.checkJoinByPhone (phone);
    }

    @GetMapping("/accept")
    public PendingFriendResponseDto getNeedAcceptFriendList() { //나한테 온 친구 신청목록 조회
        User user = UserThreadLocal.get();
        return friendService.getNeedAcceptFriendList(user);
    }
}
