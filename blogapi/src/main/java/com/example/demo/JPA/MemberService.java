package com.example.demo.JPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.project.demo.member.entity.MemberInfo;
//import com.project.demo.member.repository.MemberRepository;

@Service
public class MemberService{

	@Autowired
	MemberRepository memberRepository;

	public MemberInfo findByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId);
	}

}
