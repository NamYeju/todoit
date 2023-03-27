package app.todoit.domain.friend.dto;

import app.todoit.domain.friend.entity.FriendEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FriendResponseDto {
    List<UserInfoDto> friend_list;

    public FriendResponseDto() {
        this.friend_list = new ArrayList<>();
    }

    public void entityToDtoByMe (List<FriendEntity> entities) {
        for (FriendEntity e : entities) {
            UserInfoDto infoDto = new UserInfoDto();
            infoDto.setUserId(e.getFriend().getId());
            infoDto.setNickname(e.getFriend().getNickname());
            infoDto.setPhone(e.getFriend().getPhone());
            this.friend_list.add(infoDto);
        }
    }

    public void entityToDtoByFriend (List<FriendEntity> entities) {
        for (FriendEntity e : entities) {
            UserInfoDto infoDto = new UserInfoDto();
            infoDto.setUserId(e.getUser().getId());
            infoDto.setNickname(e.getUser().getNickname());
            infoDto.setPhone(e.getUser().getPhone());
            this.friend_list.add(infoDto);
        }
    }
}
