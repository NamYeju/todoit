package app.todoit.domain.todo.dto;

import app.todoit.domain.todo.entity.TodoTask;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoTaskDto {
    private Long taskId;
    private String task;
    private Boolean complete;

    private Boolean challenge;

    public TodoTaskDto toDto (TodoTask entity) {
        this.taskId=entity.getTodoTaskId();
        this.task=entity.getTask();
        this.complete=entity.getComplete();
        this.challenge=entity.getChallenge();
        return this;
    }

}
