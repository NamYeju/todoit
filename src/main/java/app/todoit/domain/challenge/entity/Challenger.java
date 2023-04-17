package app.todoit.domain.challenge.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import app.todoit.domain.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "challenger")
@Entity
public class Challenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "challenge_member_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "challenge_id")
	private Challenge challenge;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private InviteStatus status;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	public void setChallenge(Challenge challenge){
		this.challenge = challenge;
		if(!challenge.getChallengers().contains(this)){
			challenge.getChallengers().add(this);
		}
	}
}
