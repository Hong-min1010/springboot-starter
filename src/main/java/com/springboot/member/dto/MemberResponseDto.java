package com.springboot.member.dto;

import com.springboot.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponseDto {

    private long memberId;

    private String email;

    private String name;

    private String phone;

}
