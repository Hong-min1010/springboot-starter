package com.springboot.member.service;

import com.springboot.exeption.BusinessLogicException;
import com.springboot.exeption.ExceptionCode;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        // 중복되는 회원이 있는지 email로 확인 후 생성
        verifyExistMember(member.getEmail());
        // Repository에 저장
        return memberRepository.save(member);
    }

    // 특정 Member 수정
    public Member updateMember(Member member) {
        // 수정하려는 회원이 존재하는지 확인 후 수정
        Member findMember = findVerifiedMember(member.getMemberId());
        // Name이 존재하면 setName 실행
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        // Email이 존재하면 setEmail 실행
        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findMember.setEmail(email));
        // phone이 존재하면 setPhone 실행
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));

        // Repository에 저장
        return memberRepository.save(findMember);
    }

    // 특정 Member 조회
    public Member findMember(long memberId) {
        // memberId에 해당하는 회원 존재하면 조회

        return findVerifiedMember(memberId);
    }

    //Member 전체 조회
    public Page<Member> findCoffees(int page, int size) {
        // page와 size를 내림차순으로 전체 조회

        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }

    // Repository에서 특정 회원을 삭제하기 위해 매개변수 long memberId를 받는다
    public void deleteMember(long memberId) {
        // 삭제하려는 회원이 존재하는지 확인 후 삭제
        Member member = findVerifiedMember(memberId);
        // return 할 필요가 없기때문에 void
        memberRepository.delete(findMember(memberId));
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
