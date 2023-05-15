package app.todoit.global.exception;

import app.todoit.domain.challenge.exception.ChallengeException;
import app.todoit.domain.friend.exception.FriendException;
import app.todoit.domain.todo.exception.TodoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import app.todoit.domain.auth.exception.MemberException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> memberException(MemberException e){
		log.error("member exception : {}", e.getErrorCode().getMessage());
		return ErrorResponse.toResponse(e.getErrorCode());
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> friendException (FriendException e) {
		log.error("friend exception : {}", e.getErrorCode().getMessage());
		return ErrorResponse.toResponse(e.getErrorCode());
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> todoException (TodoException e) {
		log.error("todo exception : {}", e.getErrorCode().getMessage());
		return ErrorResponse.toResponse(e.getErrorCode());
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> challengeException (ChallengeException e) {
		log.error("challenge exception : {}", e.getErrorCode().getMessage());
		return ErrorResponse.toResponse(e.getErrorCode());
	}
}
