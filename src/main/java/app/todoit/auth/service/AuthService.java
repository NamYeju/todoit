package app.todoit.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import app.todoit.auth.dto.KakaoUserInfo;
import app.todoit.auth.entity.User;
import app.todoit.auth.exception.MemberException;
import app.todoit.auth.repository.UserRepository;
import app.todoit.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;

	public void joinUp(KakaoUserInfo kakaoUserInfo){

		Optional<User> user = userRepository.findByEmail(kakaoUserInfo.getEmail());

		if(user.isPresent())
			throw new MemberException(ErrorCode.ALREADY_EXIST_USER);

		if(user.isEmpty()){
			userRepository.save(kakaoUserInfo.toEntity());
		}


	}
}
