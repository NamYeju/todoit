package app.todoit.domain.friend.dto;

import app.todoit.domain.friend.entity.FriendEntity;
import app.todoit.domain.friend.entity.PendingFriendEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PendingFriendResponseDto {
    List<UserInfoDto> friendsList;

    @Builder
    public void entityToDto (List<PendingFriendEntity> entities) {
        friendsList = new ArrayList<>();
        for (PendingFriendEntity e : entities) {
            UserInfoDto infoDto = new UserInfoDto();
            infoDto.setNickname(e.getUser().getNickname());
            infoDto.setUserId(e.getUser().getId());
            infoDto.setPhone(e.getUser().getPhone());
            this.friendsList.add(infoDto);
        }
    }
}
