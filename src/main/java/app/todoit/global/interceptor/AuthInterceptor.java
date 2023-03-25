package app.todoit.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.auth.exception.MemberException;
import app.todoit.domain.auth.repository.UserRepository;
import app.todoit.domain.auth.token.JwtUtil;
import app.todoit.global.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

	public final static String AUTHORIZATION = "Authorization";
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;

	public AuthInterceptor(JwtUtil jwtUtil, UserRepository userRepository) {
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		log.info("interceptor start: {}", request.getRequestURI());

		String accessToken = request.getHeader(AUTHORIZATION);

		if (accessToken == null) {
			log.error("{}", "요청헤더에 토큰이 존재하지 않습니다.");
			throw new MemberException(ErrorCode.UNAUTHORIZED);
		}

		jwtUtil.validateToken(accessToken);

		String userId = jwtUtil.getSubject(accessToken);
		User user = userRepository.findById(Long.parseLong(userId))
			.orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_USER)); //TODO exception 수정
		log.info("user : {}", user.getEmail());

		UserThreadLocal.set(user);

		return true;

	}
}
