package app.todoit.domain.todo.service;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.todo.dto.GetTodoResponseDto;
import app.todoit.domain.todo.dto.TodoTaskDto;
import app.todoit.domain.todo.entity.Todo;
import app.todoit.domain.todo.entity.TodoTask;
import app.todoit.domain.todo.repository.TodoTaskRepository;
import app.todoit.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoTaskRepository todoTaskRepository;

    public GetTodoResponseDto getTodayTodo (User user, LocalDate date) {
        if (!todoRepository.findByDateAndUserId(date, user.getId()).isPresent()) {
            //새 투두 생성
            todoRepository.save(new Todo(user, date));
        }
        List<TodoTask> taskEntity = todoTaskRepository.findAllByTodoIdDate(date);
        List<TodoTaskDto> taskDto = listEntityToListDto(taskEntity);
        GetTodoResponseDto res = GetTodoResponseDto.builder()
                .date(date)
                .task(taskDto)
                .build();
        return res;
    }

    public List<TodoTaskDto> listEntityToListDto (List<TodoTask> entity) {
        List<TodoTaskDto> res = new ArrayList<>();
        for (TodoTask t : entity) {
            TodoTaskDto taskDto = new TodoTaskDto().toDto(t);
            res.add(taskDto);
        }
        return res;
    }

    public void addTask (User user, String task) {
        todoTaskRepository.save(new TodoTask(task));
    }

    public void deleteTask(Long taskId) {
        // TODO: 2023/03/28 cascade처리해야함
        todoTaskRepository.deleteById(taskId);
    }

    public void modifyTask(Long taskId, String newTask) {
        Optional<TodoTask> task = todoTaskRepository.findById(taskId);
        if (task.isPresent()) {
            task.get().setTask(newTask);
            todoTaskRepository.save(task.get());
        }
        else {

            //throws new "해당 태스크가 없습니다."
        }
    }

    public void setComplete(Long taskId) {
        Optional<TodoTask> task = todoTaskRepository.findById(taskId);
        if (task.isPresent()) {
            task.get().setComplete();
            todoTaskRepository.save(task.get());
        }
        else {
            //throws new "해당 태스크가 없습니다."
        }

    }

}
