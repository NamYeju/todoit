package app.todoit.domain.auth.api;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import app.todoit.domain.auth.dto.CodeVerifyRequest;
import app.todoit.domain.auth.dto.CommonResponse;
import app.todoit.domain.auth.dto.KakaoUserPhoneDto;
import app.todoit.domain.auth.service.SmsService;
import app.todoit.global.annotation.WithOutAuth;
import app.todoit.global.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SmsController {

	private final SmsService smsService;
	private final RedisService redisService;

	@WithOutAuth
	@PostMapping("/sms-certification")
	public ResponseEntity<CommonResponse> certify(@RequestBody KakaoUserPhoneDto phoneDto) throws UnsupportedEncodingException, NoSuchAlgorithmException,
		InvalidKeyException, JsonProcessingException {
		String code = smsService.makeCode();
		smsService.reqSms(phoneDto, code);
		redisService.saveCode(phoneDto.getEmail(), code);

		return ResponseEntity.status(200).body(CommonResponse.builder().email(phoneDto.getEmail()).message("사용자에게 인증번호를 전송하였습니다.").build());
	}

	// TODO 예외처리
	@WithOutAuth
	@PostMapping("/sms-verification")
	public ResponseEntity verify(@RequestBody CodeVerifyRequest codeVerifyRequest) {
		String savedCode = redisService.getCode(codeVerifyRequest.getEmail());

		if (codeVerifyRequest.getCode().equals(savedCode))
			return ResponseEntity.status(200)
				.body(CommonResponse.builder().email(codeVerifyRequest.getEmail()).message("인증번호가 일치합니다.").build());
		else
			return ResponseEntity.status(400)
				.body(CommonResponse.builder().email(codeVerifyRequest.getEmail()).message("인증번호가 일치하지 않습니다.").build());
	}
}
