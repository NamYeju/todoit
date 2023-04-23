package app.todoit.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import app.todoit.domain.auth.repository.UserRepository;
import app.todoit.domain.auth.token.JwtUtil;
import app.todoit.global.interceptor.AuthInterceptor;
import app.todoit.global.redis.service.RedisService;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final RedisService redisService;

	public WebConfig(JwtUtil jwtUtil, UserRepository userRepository, RedisService redisService) {
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
		this.redisService = redisService;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthInterceptor(jwtUtil, userRepository, redisService)).addPathPatterns("/api/**"); //TODO 경로수정
	}
}
