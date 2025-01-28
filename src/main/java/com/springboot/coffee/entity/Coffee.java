package com.springboot.coffee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Coffee {

    @Id
    private long coffeeId;

    @Column(nullable = false)
    private String korName;

    @Column(nullable = false)
    private String engName;

    @Column(nullable = false)
    private int price;

    @Column(length = 5, nullable = false, unique = true)
    private String coffeeCode;

    // ★★★★ Enum Class 다시 검색해보고 주석 작성 ★★★★★★
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_FOR_SALE;
    public enum CoffeeStatus {
        COFFEE_FOR_SALE("판매중"),
        COFFEE_SOLD_OUT("판매중지");

        @Getter
        private String status;


        CoffeeStatus(String status) {
            this.status = status;
        }
    }
}
