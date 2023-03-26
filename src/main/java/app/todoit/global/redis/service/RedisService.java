package app.todoit.global.redis.service;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import app.todoit.domain.auth.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisService {

	private final StringRedisTemplate redisTemplate;

	public final static long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24;
	public final static long REFRESH_TOKEN_VALIDATION_TIME = 1000L * 60 * 60 * 24 * 14;

	public void saveToken(User user, String atk, String rtk){
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

		valueOperations.set("atk:" + user.getId().toString(),  atk, Duration.ofMillis(ACCESS_TOKEN_EXPIRE_TIME));
		valueOperations.set("rtk:" + user.getId().toString(),  rtk, Duration.ofMillis(REFRESH_TOKEN_VALIDATION_TIME));

		log.info("redis atk : {}", valueOperations.get("atk:"+user.getId().toString()));
		log.info("redis rtk : {}", valueOperations.get("rtk:"+user.getId().toString()));



	}

}
