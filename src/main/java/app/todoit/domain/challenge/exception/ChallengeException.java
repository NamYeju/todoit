package app.todoit.domain.challenge.exception;

import app.todoit.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ChallengeException extends RuntimeException{
	private ErrorCode errorCode;

	public ChallengeException(ErrorCode errorCode){
		this.errorCode = errorCode;
	}
}
