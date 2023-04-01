package app.todoit.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserPhoneDto {
	private String email;
	private String phone;
}
