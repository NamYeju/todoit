package app.todoit.domain.auth.dto;

import app.todoit.domain.auth.entity.User;
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
