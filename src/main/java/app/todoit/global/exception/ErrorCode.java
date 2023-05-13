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
	LOGOUT_USER(HttpStatus.BAD_REQUEST, "로그아웃한 유저입니다"),
	FRIENDS_NOT_FOUND(HttpStatus.NOT_FOUND,"친구가 존재하지 않습니다."),
	ALREADY_FRIENDS(HttpStatus.BAD_REQUEST, "이미 친구입니다."),

	CANNOT_ACCEPT(HttpStatus.NOT_ACCEPTABLE, "친구 수락을 할 수 없습니다"),

	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),

	TODO_UNAUTHORIZED ( HttpStatus.BAD_REQUEST, "해당 태스크에 대한 권한이 없습니다"),

	TASK_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 투두 번호입니다."),

	CHALLENGE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 챌린지입니다.");

	private final HttpStatus httpStatus;
	private final String message;

}
