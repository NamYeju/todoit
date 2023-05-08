package app.todoit.domain.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.todoit.domain.challenge.entity.Challenger;

@Repository
public interface ChallengerRepository extends JpaRepository<Challenger, Long> {
	@Query("SELECT c FROM Challenger c JOIN FETCH c.user WHERE c.challenge.id = :id")
	List<Challenger> findByChallengeIdAndUser(Long id);
}
