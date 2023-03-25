package app.todoit.global.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// 400 BAD_REQUEST
	ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
	NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다"),
	FRIENDS_NOT_FOUND(HttpStatus.NOT_FOUND,"친구가 존재하지 않습니다."),
	ALREADY_FRIENDS(HttpStatus.BAD_REQUEST, "이미 친구입니다."),

	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;

}
