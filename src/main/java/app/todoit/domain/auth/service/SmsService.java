package app.todoit.domain.auth.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.todoit.domain.auth.dto.KakaoUserPhoneDto;
import app.todoit.domain.auth.dto.SmsRequestDto;
import app.todoit.domain.auth.dto.SmsResponseDto;

@Service
public class SmsService {

	private final String url;
	private final String from;
	private final String apiKey;
	private final String accessKey;
	private final String secretKey;

	public SmsService(@Value("${sms.sms_url}") String url, @Value("${sms.sms_from}") String from, @Value("${sms.sms_api_key}") String apiKey,
		@Value("${sms.sms_access_key}") String accessKey, @Value("${sms.sms_secret_key}") String secretKey){
		this.url = url;
		this.from = from;
		this.apiKey = apiKey;
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	/** 인증번호 생성 */
	public String makeCode(){
		Random random = new Random();
		String code = "";
		for(int i=0; i<4; i++){
			code += Integer.toString(random.nextInt(9));
		}
		return code;
	}

	/** 네이버에 전송할 요청헤더를 위한 */
	public String makeSignature(String time) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		String space = " ";
		String newLine = "\n";
		String method = "POST";
		String url = "/sms/v2/services/" + apiKey + "/messages";

		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(time)
			.append(newLine)
			.append(accessKey)
			.toString();

		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);

		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		String encodeBase64String = Base64.encodeBase64String(rawHmac);

		return encodeBase64String;
	}

	/** 네이버에 요청하기 */
	public void reqSms(KakaoUserPhoneDto phoneDto, String code) throws
		JsonProcessingException,
		UnsupportedEncodingException,
		NoSuchAlgorithmException,
		InvalidKeyException {

		SmsRequestDto.MessagesDto messagesDto = SmsRequestDto.MessagesDto.builder().to(phoneDto.getPhone()).build();

		List<SmsRequestDto.MessagesDto> messages = new ArrayList<>();
		messages.add(messagesDto);

		SmsRequestDto request = SmsRequestDto.builder()
			.type("SMS")
			.countryCode("82")
			.from(from)
			.subject("인증번호")
			.content("[투두잇] 본인확인 인증번호[" + code + "]를 화면에 입력하세요")
			.messages(messages)
			.build();

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(request);

		System.out.println(json);


		String time = Long.toString(System.currentTimeMillis());

		WebClient webClient = WebClient.builder().build();

		webClient.post().uri(url)
			.contentType(MediaType.APPLICATION_JSON)
			.header("x-ncp-apigw-timestamp", time)
			.header("x-ncp-iam-access-key", accessKey)
			.header("x-ncp-apigw-signature-v2", makeSignature(time))
			.bodyValue(json)
			.retrieve()
			.bodyToMono(SmsResponseDto.class)
			.subscribe();

	}
}
