package app.todoit.domain.todo.api;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.todo.dto.GetTodoResponseDto;
import app.todoit.domain.todo.dto.TodoTaskDto;
import app.todoit.domain.todo.service.TodoService;
import app.todoit.global.interceptor.UserThreadLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("")
    public GetTodoResponseDto getTodayTodo (@RequestParam(required = false) String date) {
        User user = UserThreadLocal.get();
        LocalDate now ;
        if (date==null) {
            now = LocalDate.now();
        }
        else {
            DateTimeFormatter format =DateTimeFormatter.ofPattern("yyyy-MM-yy");
            now = LocalDate.parse(date,format);
        }

        return todoService.getTodayTodo(user, now);
    }

    @PostMapping("")
    public TodoTaskDto addTask(@RequestParam String task) {
        User user = UserThreadLocal.get();
        return todoService.addTask(user, task);
    }

    @DeleteMapping("")
    public String deleteTask(@RequestParam Long taskId) {
        return todoService.deleteTask(taskId);

    }

    @PutMapping("")
    public TodoTaskDto modifyTask(@RequestParam Long taskId, @RequestParam String task) {
        return todoService.modifyTask(taskId,task);
    }

    @PostMapping("/complete")
    public TodoTaskDto setComplete(@RequestParam Long taskId) {
        return todoService.setComplete(taskId);
    }

}
