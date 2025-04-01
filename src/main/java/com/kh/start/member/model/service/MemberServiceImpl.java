package com.kh.start.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.start.member.model.dao.MemberMapper;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper; //의존성 주입을 받아서 생성
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public void signUp(MemberDTO member) {
		
		// id가 있는지 없는지 조회
		MemberDTO searchedMember = memberMapper.getMemberByMemberId(member.getMemberId());
		if(searchedMember != null) {
			throw new RuntimeException("존재하지 않는 아이디");
		}
		
		// 비밀번호 암호화
		// Role 주기
		Member memberValue = Member.builder()	
								   .memberId(member.getMemberId())
								   .memberPw(passwordEncoder.encode(member.getMemberPw()))
								   .memberName(member.getMemberName())
								   .role("ROLE_USER")
								   .build();
	
		
		memberMapper.signUp(memberValue);
		log.info("사용자 등록 성공 : {}",member);
	}

}
