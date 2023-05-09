package app.todoit.domain.challenge.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChallengeDto {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Create {

		private String title;
		private String content;
		private String[] day;
		private String[] off_day;

		@DateTimeFormat(pattern = "yyyy-mm-dd")
		private LocalDate start_date;
		@DateTimeFormat( pattern = "yyyy-mm-dd")
		private LocalDate end_date;

		private List<Challenger> friends;

	}
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Challenger{ //TODO 임시데이터
		String nickname;
		String phone;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class UserChallenge{
		private String title;
		private String content;
		private String start_date;
		private String end_date;
	}

	@Getter
	@Builder
	public static class UserChallengeResponse{
		private HttpStatus httpStatus;
		private List<UserChallenge> userChallenge;
		public static ResponseEntity<ChallengeDto.UserChallengeResponse> toResponse(List<UserChallenge> userChallenge){
			return ResponseEntity.ok().body(
				UserChallengeResponse.builder()
					.httpStatus(HttpStatus.OK)
					.userChallenge(userChallenge)
					.build()
			);
		}
	}

	@Getter
	public static class Delete {
		private String title;
	}

	@Getter
	@Builder
	public static class Response{
		private HttpStatus httpStatus;
		private String message;
		public static ResponseEntity<ChallengeDto.Response> toResponse(String message){
			return ResponseEntity.ok().body(
				Response.builder()
					.httpStatus(HttpStatus.OK)
					.message(message)
					.build()
			);
		}
	}
}
