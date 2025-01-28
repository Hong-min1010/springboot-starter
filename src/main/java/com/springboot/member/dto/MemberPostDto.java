package com.springboot.member.dto;

import com.springboot.validator.NotSpace;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberPostDto {
    @Id
    private long memberId;

    @NotSpace
    @Email
    private String email;

    @NotBlank
    private String name;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String phone;

}
