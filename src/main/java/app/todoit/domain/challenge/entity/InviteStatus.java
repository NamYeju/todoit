package app.todoit.domain.challenge.entity;

public enum InviteStatus {
	ACCEPT("수락"),
	REFUSE("거절"),
	PENDING("대기");

	private final String description;
	InviteStatus(String description){
		this.description = description;
	}

}
