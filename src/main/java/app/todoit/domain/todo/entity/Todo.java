package app.todoit.domain.todo.entity;

import app.todoit.domain.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "todo")
@Getter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "todo_id")
    private Long todoId;

    @JoinColumn(name = "user_id")
    /*
    * 연관관계의 주인에 JoinColumn을 작성. 주인이 아닌 쪽에는 mappedBy를 설정
    * 연관관계의 주인이란 외래키를 가지고 잇는 쪽.
    * name속성은 참조하는 기본키의 컬럼이름을 적는 것이 아니라 !!!해당 컬럼을 테이블에 저장할 이름을 적는것이다!!!
    * 기본키 매핑은 자동으로 됨
    * */
    @ManyToOne
    private User user;

    @Column
    private LocalDate date;

    @OneToMany(mappedBy = "taskId", cascade = CascadeType.REMOVE, orphanRemoval = true)
    /*
    *   mappedBy는 연관관계의 주인이 아닌쪽에 작성
    *   mappedBy의 값은 반대편에 자신이 매핑되어있는 >필드<명
    *  */
    List<TodoTask> task = new ArrayList<>();
    /* TODO: 2023/03/28
    *   양방향 매핑에서 update발생시  서로 데이터 동기화하는 메서드 작성하기
    * */

    public Todo (User user, LocalDate date) {
        this.user=user;
        this.date=date;
    }


}
