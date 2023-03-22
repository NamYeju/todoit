package app.todoit.domain.auth.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.todoit.domain.auth.dto.KakaoUserDto;
import app.todoit.domain.auth.dto.TokenDto;
import app.todoit.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

	private final AuthService authService;

	@PostMapping("/auth/login/kakao")
	public ResponseEntity getUserInfo(@RequestBody KakaoUserDto kakaoUserDto){
		TokenDto tokenDto = authService.joinUp(kakaoUserDto);

		return ResponseEntity.ok().body(tokenDto);
	}



}
