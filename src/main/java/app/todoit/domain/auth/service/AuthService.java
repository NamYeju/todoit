package app.todoit.domain.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import app.todoit.domain.auth.dto.JoinRequestDto;
import app.todoit.domain.auth.dto.KakaoUserDto;
import app.todoit.domain.auth.dto.TokenDto;
import app.todoit.domain.auth.entity.User;
import app.todoit.domain.auth.exception.MemberException;
import app.todoit.domain.auth.repository.UserRepository;
import app.todoit.domain.auth.token.JwtUtil;
import app.todoit.global.exception.ErrorCode;
import app.todoit.global.redis.service.RedisService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final RedisService redisService;

	public boolean isJoined(KakaoUserDto kakaoUserDto){
		Optional<User> kakaoUser = userRepository.findByEmail(kakaoUserDto.getEmail());
		if(kakaoUser.isEmpty())
			return false;
		else
			return true;
	}

	public TokenDto joinUp(JoinRequestDto joinRequestDto){

		Optional<User> kakaoUser = userRepository.findByEmail(joinRequestDto.getEmail());

		if(kakaoUser.isPresent())
			throw new MemberException(ErrorCode.ALREADY_EXIST_USER);

		User user = userRepository.save(joinRequestDto.toEntity());

		String atk = jwtUtil.generateAccessToken(user);
		String rtk = jwtUtil.generateRefreshToken(user);

		redisService.saveToken(user, atk, rtk);

		return TokenDto.builder().email(user.getEmail())
			.accessToken(atk).refreshToken(rtk).build();
	}

	public TokenDto login(KakaoUserDto kakaoUserDto){
		User reqUser = kakaoUserDto.toEntity();
		Optional<User> user = userRepository.findByEmail(reqUser.getEmail());

		String atk = jwtUtil.generateAccessToken(user.get());
		String rtk = jwtUtil.generateRefreshToken(user.get());

		redisService.saveToken(user.get(), atk, rtk);

		return TokenDto.builder().email(user.get().getEmail())
			.accessToken(atk).refreshToken(rtk).build();
	}
}
