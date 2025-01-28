package com.springboot.member.service;

import com.springboot.exeption.BusinessLogicException;
import com.springboot.exeption.ExceptionCode;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    public final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Member 생성
    public Member createMember(Member member) {
        // 중복되는 회원이 있는지 확인 후 생성
        verifyExistMember(member.getEmail());

        return memberRepository.save(member);
    }

    // 특정 Member 수정
    public Member updateMember(Member member) {
        // 수정하려는 회원이 존재하는지 확인 후 수정
        Member findMember = findVerifiedMember(member.getMemberId());
        // member.getName을 통해 Name이 null인지 아닌지 확인 후 있다면 setName 실행
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findMember.setEmail(email));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));

        return memberRepository.save(findMember);
    }

    // 특정 Member 조회
    public Member findMember(Member member) {

        return findVerifiedMember(member.getMemberId());
    }

    //Member 전체 조회
    public Member findAllMembers() {

        return (Member) memberRepository.findAll();
    }

    public void deleteMember(long memberId) {
        // 삭제하려는 회원이 존재하는지 확인 후 삭제
        Member member = findVerifiedMember(memberId);
        memberRepository.delete(findMember(member));

    }

    // 해당 Member가 존재하는지 확인하는 코드
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    // 해당 Member가 중복되는지 확인
    public void verifyExistMember(String email) {
    Optional<Member> member = memberRepository.findByEmail(email);
    if (member.isPresent())
        throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
