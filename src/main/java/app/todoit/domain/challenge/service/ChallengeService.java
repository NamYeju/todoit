package app.todoit.domain.challenge.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.auth.exception.MemberException;
import app.todoit.domain.auth.repository.UserRepository;
import app.todoit.domain.challenge.dto.ChallengeDto;
import app.todoit.domain.challenge.entity.Challenge;
import app.todoit.domain.challenge.entity.Challenger;
import app.todoit.domain.challenge.entity.InviteStatus;
import app.todoit.domain.challenge.entity.Role;
import app.todoit.domain.challenge.repository.ChallengeRepository;
import app.todoit.domain.challenge.repository.ChallengerRepository;
import app.todoit.domain.todo.entity.Todo;
import app.todoit.domain.todo.entity.TodoTask;
import app.todoit.domain.todo.repository.TodoRepository;
import app.todoit.domain.todo.repository.TodoTaskRepository;
import app.todoit.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeService {

	private final UserRepository userRepository;
	private final ChallengeRepository challengeRepository;
	private final ChallengerRepository challengerRepository;
	private final TodoRepository todoRepository;
	private final TodoTaskRepository todoTaskRepository;

	@Transactional
	public ResponseEntity<ChallengeDto.Response> registerChallenge(User user, ChallengeDto.Create request) {

		// 챌린지 생성
		Challenge newChallenge = Challenge.builder()
			.title(request.getTitle())
			.content(request.getContent())
			.day(Arrays.toString(request.getDay()))
			.off_day(Arrays.toString(request.getOff_day()))
			.start_date(request.getStart_date())
			.end_date(request.getEnd_date())
			.status(true)
			.build();

		challengeRepository.save(newChallenge);

		// // 챌린지를 생성한 챌린저 DB에 저장
		Challenger leader = Challenger.builder()
			.challenge(newChallenge)
			.user(user)
			.status(InviteStatus.ACCEPT) //TODO enum 값 모호함
			.role(Role.Leader)
			.startDate(request.getStart_date())
			.build();

		leader.setUser(user);
		user.getUserInChallenge().add(leader);

		leader.setChallenge(newChallenge);
		newChallenge.getChallengers().add(leader);

		challengerRepository.save(leader);


		// 챌린지 초대 보내기
		if(request.getFriends().size() != 0){
			request.getFriends().stream()
				.map(f -> {
					Optional<User> newUser = userRepository.findByPhone(f.getPhone());
					if(newUser.isPresent())
						return inviteChallengers(newChallenge, newUser.get());
					else throw new MemberException(ErrorCode.NOT_FOUND_USER);
				})
				.forEach(challengerRepository::save);
		}
		if(request.getFriends().size() == 0){
			log.info("친구신청 존재 x");
		}

		linkChallengeTodo(user, newChallenge);

		return ChallengeDto.Response.toResponse("챌린지가 성공적으로 등록되었습니다.");

	}
	public Challenger inviteChallengers(Challenge challenge, User user){
		return Challenger.builder()
			.challenge(challenge)
			.user(user)
			.status(InviteStatus.PENDING)
			.role(Role.Member)
			.startDate(challenge.getStart_date()) // TODO 초대 날짜에 따라 개인 시작 날짜 다르게
			.build();
	}

	public void linkChallengeTodo(User user, Challenge challenge){
		getDatesBetweenTwoDates(challenge.getStart_date(), challenge.getEnd_date())
			.stream()
				.forEach(date -> {
					Optional<Todo> todo = todoRepository.findByDateAndUserId(date, user.getId());
					Todo todoEntity;
					if(todo.isEmpty()){
						// 새로운 투두 객체 생성
						todoEntity = todoRepository.save(new Todo(user, date));
					}
					else{
						todoEntity = todo.get(); // 기존 투두 객체
					}
					// 챌린지용 투두 task 객체
					TodoTask todoTask = TodoTask.builder()
						.todo(todoEntity)
						.task(challenge.getTitle())
						.complete(false)
						.isFromChallenge(true)
						.challenge(challenge)
						.build();

					todoTaskRepository.save(todoTask);
				});
	}

	// 두 날짜 사이에 존재하는 날짜들
	public static List<LocalDate> getDatesBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
		return startDate.datesUntil(endDate)
			.collect(Collectors.toList());
	}

	// 챌린지 삭제
	@Transactional
	public ResponseEntity<ChallengeDto.Response> deleteChallenge(User requestUser, String title){
		List<Challenge> challengeList = challengeRepository.findByTitle(title);

		for(Challenge c1 : challengeList){
			List<Challenger> challengerList = challengerRepository.findByChallengeIdAndUser(c1.getId());
			for(Challenger c2 : challengerList){
				if(c2.getUser().getId().equals(requestUser.getId())) {
					List<TodoTask> deleteTask = todoTaskRepository.findByChallenge(c2.getChallenge());
					for(TodoTask task : deleteTask)
						todoTaskRepository.deleteById(task.getTaskId());
					challengerRepository.deleteById(c2.getId());
					challengeRepository.deleteById(c2.getChallenge().getId());
				}
			}
		}
		return ChallengeDto.Response.toResponse("챌린지가 삭제되었습니다.");
	}
}
