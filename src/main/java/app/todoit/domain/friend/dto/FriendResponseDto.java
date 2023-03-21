package app.todoit.domain.friend.dto;

import app.todoit.domain.friend.entity.FriendEntity;
import lombok.Builder;

import java.util.List;

public class FriendResponseDto {
    List<UserInfoDto> friendsList;

    @Builder
    public void entityToDto (List<FriendEntity> entities) {
        for (FriendEntity e : entities) {
            UserInfoDto infoDto = new UserInfoDto();
            infoDto.setUserId(e.getFriendId().getUserId());
            infoDto.setPhone(e.getFriend().getPhone());
            this.friendsList.add(infoDto);
        }
    }
}
