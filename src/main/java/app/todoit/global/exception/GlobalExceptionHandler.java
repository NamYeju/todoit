package app.todoit.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import app.todoit.auth.exception.MemberException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> memberException(MemberException e){
		log.info("member exception = {}", e.getErrorCode().getMessage());

		return ErrorResponse.toResponse(e.getErrorCode());
	}
}
