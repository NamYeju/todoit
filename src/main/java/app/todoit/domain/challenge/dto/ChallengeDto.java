package app.todoit.domain.challenge.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChallengeDto {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Request {
		private String title;
		private String[] day;
		private String[] off_day;
		private String start_date;
		private String end_date;
		private List<Challenger> friends;
		private Challenger inviter;

	}
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Challenger{
		String nickname;
		String phone;
	}

}
