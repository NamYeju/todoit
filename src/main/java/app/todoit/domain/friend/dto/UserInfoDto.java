package app.todoit.domain.friend.dto;

import lombok.Data;

@Data
public class UserInfoDto {
    private String nickname;
    private Long userId;
    private String phone;
}
