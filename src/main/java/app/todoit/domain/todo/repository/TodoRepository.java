package app.todoit.domain.todo.repository;

import app.todoit.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    public Optional<Todo> findByDateAndUserId (LocalDate date, Long id);
}
