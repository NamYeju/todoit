package app.todoit.domain.friend.api;

import app.todoit.domain.friend.dto.FriendResponseDto;
import app.todoit.domain.friend.dto.PendingFriendResponseDto;
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
    public String addFriend (@RequestParam Long friendId) { //친구 신청
        return friendService.addFriend(3L,friendId);
    }

    @GetMapping("/friend")
    public FriendResponseDto getFriendList() {
        return friendService.getFriendsList(1L);
    }

    @DeleteMapping("/friend")
    public String deleteFriend(@RequestParam Long friendId) { //친구 삭제

        return friendService.deleteFriend(1L,friendId);
    }

    @GetMapping("/friend/pending")
    public PendingFriendResponseDto getPendingFriendList() { //신청 대기 목록 조회
        return friendService.getPendingFriends(2L);
    }

    @PostMapping("/friend/accept")
    public String acceptFriend(@RequestParam Long friendId) { //친구 수락
        return friendService.acceptFriend(1L,friendId);
    }
}
