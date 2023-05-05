package app.todoit.domain.challenge.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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

}
