package app.todoit.domain.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Table(name = "challenge")
@Entity
public class Challenge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "challenge_id")
	private Long id;

	private String title;
	private String content;
	private String day;
	private String off_day;
	private String start_date;
	private String end_date;
	private boolean status;
}
