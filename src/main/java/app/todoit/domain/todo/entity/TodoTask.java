package app.todoit.domain.todo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "todo_task")
@Getter
@Setter
@NoArgsConstructor
public class TodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todo_task_id")
    private Long taskId;

    @JoinColumn(name = "todo_id")
    @ManyToOne()
    private Todo todo;

    @Column
    private String task;

    @Column
    private Boolean complete;

    @Column
    private Boolean challenge;

//    @Column(name = "challenge_id")
//    @ManyToOne
//    @JoinColumn
//    private Long challengeId; //챌린지 엔티티 정해지면 타입을 챌린지로 변경해야함

    public TodoTask (String task, Todo todo) {
        this.task=task;
        this.complete=false;
        this.challenge=false;
        this.todo= todo;
    }

    public void setComplete () {
        if(complete)
            complete=false;
        else complete=true;
    }


}
