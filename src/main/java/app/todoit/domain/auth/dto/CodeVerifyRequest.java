package app.todoit.domain.auth.dto;

import lombok.Getter;

@Getter
public class CodeVerifyRequest {
	private String email;
	private String code;
}
