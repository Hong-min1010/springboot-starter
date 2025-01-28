package com.springboot.member.mapper;

import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    Member memberResponseDtoToMember(MemberResponseDto memberResponseDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
}
