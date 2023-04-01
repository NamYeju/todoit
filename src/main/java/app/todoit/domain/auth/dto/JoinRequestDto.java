package app.todoit.domain.auth.dto;

import app.todoit.domain.auth.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JoinRequestDto {
	private String email;
	private String nickname;
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
