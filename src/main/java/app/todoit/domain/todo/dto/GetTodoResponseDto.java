package app.todoit.domain.todo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class GetTodoResponseDto {
    private LocalDate date;
    private List<TodoTaskDto> task;

}
