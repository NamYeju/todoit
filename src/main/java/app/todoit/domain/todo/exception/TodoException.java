package app.todoit.domain.todo.exception;

import app.todoit.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class TodoException extends RuntimeException{
    private ErrorCode errorCode;

    public TodoException (ErrorCode errorCode) {
        this.errorCode =errorCode;
    }

}
