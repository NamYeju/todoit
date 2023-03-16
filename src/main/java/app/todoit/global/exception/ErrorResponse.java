package app.todoit.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
	private final HttpStatus httpStatus;
	private final String message;

	public static ResponseEntity<ErrorResponse> toResponse(ErrorCode errorCode){
		return ResponseEntity
				.status(errorCode.getHttpStatus())
				.body(ErrorResponse
						.builder().message(errorCode.getMessage()).build());
	}
}
