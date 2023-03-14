package app.todoit.auth.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.todoit.auth.dto.KakaoUserInfo;
import app.todoit.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

	private final AuthService authService;

	@PostMapping("/auth/login/kakao")
	public void getUserInfo(@RequestBody KakaoUserInfo kakaoUserInfo){
		authService.joinUp(kakaoUserInfo);

	}



}
