package app.todoit.domain.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "challenge_member")
@Entity
public class ChallengeMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "challenge_member_id")
	private Long id;

	@Column(name = "challenge_id")
	private Long challengeId;

	@Column(name = "user_id")
	private Long userId;

	private boolean status;
	private String role;

	@Column(name = "start_date")
	private String startDate;
}
