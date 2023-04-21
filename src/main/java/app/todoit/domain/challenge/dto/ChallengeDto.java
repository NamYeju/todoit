package app.todoit.domain.challenge.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
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

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
		private Date start_date;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
		private Date end_date;

		private List<Challenger> friends;

	}
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Challenger{ //TODO 임시데이터
		String nickname;
		String phone;
	}

}