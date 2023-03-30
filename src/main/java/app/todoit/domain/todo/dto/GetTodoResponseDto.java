package app.todoit.domain.todo.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GetTodoResponseDto {
    private LocalDate date;
    private List<TodoTaskDto> task;

    public GetTodoResponseDto(LocalDate date) {
        this.date = date;
        this.task = new ArrayList<>();
    }
}
