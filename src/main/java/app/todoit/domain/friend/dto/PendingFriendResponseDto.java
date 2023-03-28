package app.todoit.domain.friend.dto;

import app.todoit.domain.friend.entity.PendingFriendEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PendingFriendResponseDto {
    List<UserInfoDto> friend_list;

    @Builder
    public void entityToDto (List<PendingFriendEntity> entities) {
        friend_list = new ArrayList<>();
        for (PendingFriendEntity e : entities) {
            UserInfoDto infoDto = new UserInfoDto();
            infoDto.setNickname(e.getUser().getNickname());
            infoDto.setUserId(e.getUser().getId());
            infoDto.setPhone(e.getUser().getPhone());
            this.friend_list.add(infoDto);
        }
    }
}
