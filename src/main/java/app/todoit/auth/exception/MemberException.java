package app.todoit.auth.exception;

import app.todoit.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

	private ErrorCode errorCode;

	public MemberException(ErrorCode errorCode){
		this.errorCode = errorCode;
	}

}
