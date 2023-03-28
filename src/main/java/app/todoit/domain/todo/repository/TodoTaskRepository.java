package app.todoit.domain.todo.repository;

import app.todoit.domain.todo.entity.TodoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoTaskRepository extends JpaRepository<TodoTask,Long> {
    //@Query(value = "SELECT k FROM TodoTask k JOIN FETCH k.todoId t WHERE t.date = :date ")
    List<TodoTask> findAllByTodoIdDate (LocalDate date);

}
