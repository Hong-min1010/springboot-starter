package com.springboot.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
public class CoffeeResponseDto {

    private long coffeeId;

    private String korName;

    private String engName;

    private int price;

}
