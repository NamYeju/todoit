package app.todoit.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class KakaoUserResponse {
	private String nickname;
	private String email;
	private String isJoined;
	private String message;
}
