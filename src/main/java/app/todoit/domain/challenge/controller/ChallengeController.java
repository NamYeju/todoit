package app.todoit.domain.challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	/** 챌린지 조회 */
	@GetMapping("")
	public ResponseEntity<ChallengeDto.UserChallengeResponse> getChallengeList(){
		User user = UserThreadLocal.get();
		return challengeService.getUserChallenge(user);
	}

	/** 챌린지 삭제 */
	@DeleteMapping("")
	public ResponseEntity<ChallengeDto.Response> deleteChallenge(@RequestBody ChallengeDto.Delete requestDto){
		User user = UserThreadLocal.get();
		return challengeService.deleteChallenge(user, requestDto.getTitle());
	}

	/** 신청 온 챌린지 목록*/
	@GetMapping("/invite")
	public ResponseEntity<ChallengeDto.ChallengeResponse> getInvitedChallengeList(){
		User user = UserThreadLocal.get();
		return ChallengeDto.ChallengeResponse.toResponse(challengeService.invitedChallengeList(user));
	}

	/** 신청 온 챌린지 수락 */
	@PostMapping ("/accept")
	public ResponseEntity<ChallengeDto.Response> acceptInvitedChallenge(@RequestParam Long challengeId){
		User user = UserThreadLocal.get();
		return challengeService.acceptChallenge(user, challengeId);
	}
	/** 신청 온 챌린지 거절 */
	@PostMapping ("/refuse")
	public ResponseEntity<ChallengeDto.Response> refuseInvitedChallenge(@RequestParam Long challengeId){
		User user = UserThreadLocal.get();
		return challengeService.refuseChallenge(user, challengeId);
	}

}
