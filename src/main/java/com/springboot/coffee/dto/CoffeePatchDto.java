package com.springboot.coffee.dto;

import com.springboot.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CoffeePatchDto {
    private long coffeeId;

    @NotSpace(message = "공백이 없어야 합니다.")
    private String korName;

    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$", message = "올바른 문자를 입력해주세요.")
    private String engName;

    @Range(min= 100, max= 50000)
    private Integer price;


}
