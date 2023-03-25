package app.todoit.global.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.auth.repository.UserRepository;
import app.todoit.domain.auth.token.JwtUtil;
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

	/** controller로 요청 가기 전에 수행 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		log.info("interceptor start: {}", request.getRequestURI());

		String accessToken = request.getHeader(AUTHORIZATION);

		if(accessToken.length() > 0){
			boolean b = jwtUtil.validateToken(accessToken);
			log.info("result : {}", b);
			String userId = jwtUtil.getSubject(accessToken);
			Optional<User> user = userRepository.findById(Long.parseLong(userId));
			log.info("user:{}", user.get().getEmail());
			UserThreadLocal.set(user);

		}
		else if(accessToken.length() == 0){  //TODO 예외처리
			log.info("Empty token");
			throw new Exception();
		}

		return true;
		//return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
