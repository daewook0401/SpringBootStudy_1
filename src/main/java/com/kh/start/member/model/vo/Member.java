package com.kh.start.member.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

// Value Object
// V     O 

@Value
@Getter
@Builder
public class Member {
	
	/*
	 * vo객체의 특징 -> 불변성 (setter가 없음)
	 * 
	 */
	private Long MemberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String role;

	
}
