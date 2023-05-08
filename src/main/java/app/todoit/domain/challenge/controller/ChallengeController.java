package app.todoit.domain.challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.todoit.domain.auth.entity.User;
import app.todoit.domain.challenge.dto.ChallengeDto;
import app.todoit.domain.challenge.service.ChallengeService;
import app.todoit.global.interceptor.UserThreadLocal;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {

	private final ChallengeService challengeService;

	/** 챌린지 추가 */
	@PostMapping("")
	public ResponseEntity<ChallengeDto.Response> createChallenge(@RequestBody ChallengeDto.Create requestDto) {
		User user = UserThreadLocal.get();
		return challengeService.registerChallenge(user, requestDto);
	}

	/** 챌린지 삭제 */
	@DeleteMapping("")
	public ResponseEntity<ChallengeDto.Response> deleteChallenge(@RequestBody ChallengeDto.Delete requestDto){
		User user = UserThreadLocal.get();
		return challengeService.deleteChallenge(user, requestDto.getTitle());
	}
}
