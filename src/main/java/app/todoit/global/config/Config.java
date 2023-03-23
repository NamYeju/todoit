package app.todoit.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import app.todoit.domain.auth.repository.UserRepository;
import app.todoit.domain.auth.token.JwtUtil;
import app.todoit.global.interceptor.TokenInterceptor;

@Configuration
public class Config implements WebMvcConfigurer {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;

	public Config(JwtUtil jwtUtil, UserRepository userRepository) {
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//TODO interceptor 경로 수정
		registry.addInterceptor(new TokenInterceptor(jwtUtil, userRepository)).addPathPatterns("/**");
	}
}
