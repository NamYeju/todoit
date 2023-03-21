package app.todoit.domain.friend.dto;

import app.todoit.domain.friend.entity.FriendEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FriendResponseDto {
    List<UserInfoDto> friendsList;

    public FriendResponseDto() {
        this.friendsList = new ArrayList<>();
    }

    public void entityToDtoByMe (List<FriendEntity> entities) {
        for (FriendEntity e : entities) {
            UserInfoDto infoDto = new UserInfoDto();
            infoDto.setUserId(e.getFriend().getId());
            infoDto.setNickname(e.getFriend().getNickname());
            infoDto.setPhone(e.getFriend().getPhone());
            this.friendsList.add(infoDto);
        }
    }

    public void entityToDtoByFriend (List<FriendEntity> entities) {
        for (FriendEntity e : entities) {
            UserInfoDto infoDto = new UserInfoDto();
            infoDto.setUserId(e.getUser().getId());
            infoDto.setNickname(e.getUser().getNickname());
            infoDto.setPhone(e.getUser().getPhone());
            this.friendsList.add(infoDto);
        }
    }
}
