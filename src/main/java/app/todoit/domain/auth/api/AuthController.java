package app.todoit.domain.auth.api;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import app.todoit.domain.auth.dto.KakaoUserDto;
import app.todoit.domain.auth.dto.TokenDto;
import app.todoit.domain.auth.entity.User;
import app.todoit.domain.auth.service.AuthService;
import app.todoit.global.annotation.WithOutAuth;
import app.todoit.global.interceptor.UserThreadLocal;
import lombok.RequiredArgsConstructor;
import lombok.With;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {

	private final AuthService authService;

	@WithOutAuth
	@PostMapping("/api/auth/kakao")
	public ResponseEntity getUserInfo(@RequestBody KakaoUserDto kakaoUserDto){
		TokenDto tokenDto = authService.joinUp(kakaoUserDto);
		return ResponseEntity.ok().body(tokenDto);
	}
}
