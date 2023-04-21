package app.todoit.domain.auth.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import app.todoit.domain.challenge.entity.Challenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String nickname;
	private String email;
	private String phone;

	private String social;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Challenger> userInChallenge = new ArrayList<>();

	public void add(Challenger challenger){
		this.userInChallenge.add(challenger);
	}
}
