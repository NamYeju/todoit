package app.todoit.domain.friend.exception;

import app.todoit.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FriendException extends RuntimeException{
    private ErrorCode errorCode;

    public FriendException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
