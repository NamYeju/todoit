package app.todoit.auth.service;

import org.springframework.stereotype.Service;

import app.todoit.auth.dto.KakaoUserInfo;
import app.todoit.auth.entity.KakaoUser;
import app.todoit.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;

	public void joinUp(KakaoUserInfo kakaoUserInfo){
		userRepository.save(KakaoUser.builder()
				.nickname(kakaoUserInfo.getNickname())
				.email(kakaoUserInfo.getEmail())
				.phone(kakaoUserInfo.getPhone())
				.social("Kakao")
				.build());
	}
}
