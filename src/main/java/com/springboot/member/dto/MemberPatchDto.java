package com.springboot.member.dto;

import com.springboot.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberPatchDto {
    private long memberId;

    @NotSpace
    @Email
    private String email;

    @NotBlank
    private String name;

    @Pattern(regexp = "regexp = \"^010-\\\\d{3,4}-\\\\d{4}$\",\n" +
            "message = \"휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.\"")
    private String phone;

}
