package app.todoit.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.todoit.auth.entity.KakaoUser;

@Repository
public interface UserRepository extends JpaRepository<KakaoUser, Long> {
}
