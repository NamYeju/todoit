package app.todoit.auth.dto;

import app.todoit.auth.entity.User;
import lombok.Getter;

@Getter
public class KakaoUserDto {
	private String nickname;
	private String email;
	private String phone;

	public User toEntity(){
		return User.builder()
			.email(email)
			.nickname(nickname)
			.phone(phone)
			.social("kakao")
			.build();
	}
}
