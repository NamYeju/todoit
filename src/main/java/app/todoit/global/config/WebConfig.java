package app.todoit.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import app.todoit.domain.auth.repository.UserRepository;
import app.todoit.domain.auth.token.JwtUtil;
import app.todoit.global.interceptor.AuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;

	public WebConfig(JwtUtil jwtUtil, UserRepository userRepository) {
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthInterceptor(jwtUtil, userRepository)).addPathPatterns("/api/**"); //TODO 경로수정
	}
}
