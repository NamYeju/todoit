package app.todoit.domain.challenge.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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
	private Date start_date;
	private Date end_date;
	private boolean status;

	@OneToMany(mappedBy = "challenge")
	private List<Challenger> challengers = new ArrayList<>();

	void addChallenger(Challenger challenger){
		this.challengers.add(challenger);
		if(challenger.getChallenge() != this){
			challenger.setChallenge(this);
		}

	}
}
