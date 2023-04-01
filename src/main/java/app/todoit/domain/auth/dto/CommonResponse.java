package app.todoit.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse {
	String email;
	String message;
}
