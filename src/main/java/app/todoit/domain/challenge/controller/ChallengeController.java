package app.todoit.domain.challenge.controller;

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

	@PostMapping("")
	public void createChallenge(@RequestBody ChallengeDto.Create requestDto) {
		User user = UserThreadLocal.get();
		challengeService.registerChallenge(user, requestDto);
	}
}
