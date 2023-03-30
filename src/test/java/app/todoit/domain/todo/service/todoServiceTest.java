//package app.todoit.domain.todo.service;
//
//import app.todoit.domain.auth.entity.User;
//import app.todoit.domain.auth.repository.UserRepository;
//import app.todoit.domain.todo.dto.GetTodoResponseDto;
//import app.todoit.domain.todo.dto.TodoTaskDto;
//import app.todoit.domain.todo.repository.TodoRepository;
//import app.todoit.domain.todo.repository.TodoTaskRepository;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class todoServiceTest {
//
//    @Autowired
//    TodoService todoService;
//    @Autowired
//    TodoRepository todoRepository;
//    @Autowired
//    TodoTaskRepository todoTaskRepository;
//
//    @Autowired
//    UserRepository userRepository;
//    @Test
//    public void getTodayTodo () {
//        User user = userRepository.findById(2L).get();
//        GetTodoResponseDto todo = todoService.getTodayTodo(user, LocalDate.now());
//        assertEquals(LocalDate.now(),todo.getDate());
//
//    }
//
//    @Test
//    public void addTask() {
//        User user = userRepository.findById(2L).get();
//        todoService.addTask(user,"물 한잔 마시기!");
//        assertThat(todoTaskRepository.findAllByTask("물 한잔 마시기!"));
//    }
//
//    @Test
//    public void checkComplete() {
//        //default = false;
//        todoService.setComplete(4L);
//        assertThat(todoTaskRepository.findById(4L).get().getComplete()); //assert true;
//
//    }
//
//    @Test
//    public void deleteTodo () {
//        todoService.deleteTask(4L);
//        assertThat(!todoTaskRepository.findById(4L).isPresent());
//    }
//
//}
