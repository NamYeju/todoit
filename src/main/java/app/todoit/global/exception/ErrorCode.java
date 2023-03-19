package app.todoit.global.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// 400 BAD_REQUEST
	ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.")
	;

	private HttpStatus httpStatus;
	private String message;
}
