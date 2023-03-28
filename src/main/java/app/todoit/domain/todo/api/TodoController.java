package app.todoit.domain.todo.api;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.todo.dto.GetTodoResponseDto;
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
        if (date.isEmpty()) {
            now = LocalDate.now();
        }
        else {
            DateTimeFormatter format =DateTimeFormatter.ofPattern("yyyy-MM-yy");
            now = LocalDate.parse(date,format);
        }

        return todoService.getTodayTodo(user, now);
    }

    @PostMapping("")
    public void addTask(@RequestBody String task) {
        User user = UserThreadLocal.get();
        todoService.addTask(user, task);
    }

    @DeleteMapping("")
    public void deleteTask(@RequestParam Long taskId) {
        todoService.deleteTask(taskId);

    }

    @PutMapping("")
    public void modifyTask(@RequestParam Long taskId, @RequestParam String task) {
        todoService.modifyTask(taskId,task);
    }

    @PostMapping("/complete")
    public void setComplete(@RequestParam Long taskId) {
        todoService.setComplete(taskId);
    }

}
