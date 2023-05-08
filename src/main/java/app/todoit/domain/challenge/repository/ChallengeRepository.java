package app.todoit.domain.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.todoit.domain.challenge.entity.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
	List<Challenge> findByTitle(String title);
}
