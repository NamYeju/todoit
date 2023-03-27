package app.todoit.domain.friend.dto;

import app.todoit.domain.auth.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JoinCheckDto {
    List<UserInfoDto> join_check;

    public JoinCheckDto(List<User> userEntities) {
        join_check = new ArrayList<>();
        for (User u : userEntities) {
            UserInfoDto infoDto = new UserInfoDto();
            infoDto.setUserId(u.getId());
            infoDto.setPhone(u.getPhone());
            infoDto.setNickname(u.getNickname());
            join_check.add(infoDto);
        }
    }
}
