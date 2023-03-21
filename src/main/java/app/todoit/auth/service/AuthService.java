package app.todoit.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import app.todoit.auth.dto.KakaoUserDto;
import app.todoit.auth.dto.TokenDto;
import app.todoit.auth.entity.User;
import app.todoit.auth.exception.MemberException;
import app.todoit.auth.repository.UserRepository;
import app.todoit.auth.token.JwtUtil;
import app.todoit.global.exception.ErrorCode;
import app.todoit.global.redis.service.RedisService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final RedisService redisService;

	public TokenDto joinUp(KakaoUserDto kakaoUserDto){

		Optional<User> kakaoUser = userRepository.findByEmail(kakaoUserDto.getEmail());

		if(kakaoUser.isPresent())
			throw new MemberException(ErrorCode.ALREADY_EXIST_USER);

		User user = userRepository.save(kakaoUserDto.toEntity());

		String atk = jwtUtil.generateAccessToken(user);
		String rtk = jwtUtil.generateRefreshToken(user);

		redisService.saveToken(user, atk, rtk);

		return TokenDto.builder().email(user.getEmail())
			.accessToken(atk).refreshToken(rtk).build();
	}
}
