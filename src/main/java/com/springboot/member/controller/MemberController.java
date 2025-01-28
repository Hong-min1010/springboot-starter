package com.springboot.member.controller;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.entity.Member;
import com.springboot.member.mapper.MemberMapper;
import com.springboot.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/v1/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, Member member, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

//    @PostMapping
//    public ResponseEntity postMember(MemberPostDto memberPostDto) {
//        Member member = memberService.createMember(memberMapper.memberPostDtoToMember(memberPostDto));
//
//        return null;
//    }
}
