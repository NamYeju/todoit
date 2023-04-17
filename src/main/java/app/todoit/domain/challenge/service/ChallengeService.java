package app.todoit.domain.challenge.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.auth.repository.UserRepository;
import app.todoit.domain.challenge.dto.ChallengeDto;
import app.todoit.domain.challenge.entity.Challenge;
import app.todoit.domain.challenge.entity.Challenger;
import app.todoit.domain.challenge.entity.InviteStatus;
import app.todoit.domain.challenge.entity.Role;
import app.todoit.domain.challenge.repository.ChallengeRepository;
import app.todoit.domain.challenge.repository.ChallengerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeService {

	private final UserRepository userRepository;
	private final ChallengeRepository challengeRepository;
	private final ChallengerRepository challengerRepository;

	public void registerChallenge(User user, ChallengeDto.Create request) {

		// 챌린지 생성
		Challenge newChallenge = Challenge.builder()
			.title(request.getTitle())
			.content(null)
			.day(Arrays.toString(request.getDay()))
			.off_day(Arrays.toString(request.getOff_day()))
			.start_date(request.getStart_date())
			.end_date(request.getEnd_date())
			.status(true)
			.build();

		challengeRepository.save(newChallenge);

		// 챌린지를 생성한 챌린저 DB에 저장
		Challenger leader = Challenger.builder()
			.challenge(newChallenge)
			.user(user)
			.status(InviteStatus.ACCEPT) //TODO enum 값 모호함
			.role(Role.Leader)
			.startDate(request.getStart_date())
			.build();

		challengerRepository.save(leader);

		newChallenge.addChallenger(leader);
		leader.setChallenge(newChallenge);

		// 챌린지 초대 보내기
		if(request.getFriends().isPresent()){
			request.getFriends().stream()
				.map(f -> {
					Optional<User> newUser = userRepository.findByPhone(f.getPhone());
					return inviteChallengers(newChallenge, newUser.get());
				})
				.forEach(c -> challengerRepository.save(c));
		}


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
}
