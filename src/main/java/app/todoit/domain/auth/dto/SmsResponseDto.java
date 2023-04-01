package app.todoit.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SmsResponseDto {
	private String requestId;
	private String requestTime;
	private String statusCode;
	private String statusName;
}

