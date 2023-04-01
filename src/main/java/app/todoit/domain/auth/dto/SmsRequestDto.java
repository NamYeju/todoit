package app.todoit.domain.auth.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsRequestDto {
	/**
	 * {
	 *     "from":"string", //발신번호
	 *     "subject":"string", //제목
	 *     "content":"string", //content 내용
	 *     "messages":[
	 *         {
	 *             "to":"string" // 수신번호
	 *         }
	 *     ],
	 * }
	 * */
	String type;
	String countryCode;
	String from;
	String subject;
	String content;
	List<MessagesDto> messages;

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	static public class MessagesDto{
		String to;
	}


}
