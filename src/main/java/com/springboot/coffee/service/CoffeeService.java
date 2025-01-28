package com.springboot.coffee.service;


import com.springboot.coffee.dto.CoffeePostDto;
import com.springboot.coffee.entity.Coffee;
import com.springboot.coffee.repository.CoffeeRepository;
import com.springboot.exeption.BusinessLogicException;
import com.springboot.exeption.ExceptionCode;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {
    public final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public Coffee createCoffee(Coffee coffee) {
        // 중복된 coffeeCode가 있는지 확인
        verifyExistsCoffee(coffee.getCoffeeCode());
        // 중복 확인 후 중복되지 않은 coffeeCode면 coffeeCode에 Set
        coffee.setCoffeeCode(coffee.getCoffeeCode());
        // set한 coffee 객체 coffeeRepository에 저장
        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(Coffee coffee) {
        // update를 하려면 Coffee가 있는지부터 검증 후
        findVerifiedCoffee(coffee.getCoffeeId());
        // Optional 객체가 값을 가지고 있으면 변경 korName -> 변경
        Optional.ofNullable(coffee.getKorName())
                .ifPresent(korName -> coffee.setKorName(coffee.getKorName()));
        // Optional 객체가 값을 가지고 있으면 변경 engName -> 변경
        Optional.ofNullable(coffee.getEngName())
                .ifPresent(engName -> coffee.setEngName(coffee.getEngName()));
        // Optional 객체가 값을 가지고 있으면 변경 Price -> 변경
        Optional.ofNullable(coffee.getPrice())
                .ifPresent(price -> coffee.setPrice(coffee.getPrice()));
        // Optional 객체가 값을 가지고 있으면 변경 coffeeStatus -> 변경
        Optional.ofNullable(coffee.getCoffeeStatus())
                .ifPresent(status -> coffee.setCoffeeStatus(coffee.getCoffeeStatus()));
        // set한 coffee 객체 coffeeRepository에 저장
        return coffeeRepository.save(coffee);
    }

    public Coffee findCoffee(Coffee coffee) {

        // 해당하는 Coffee가 존재하는지 확인 후 존재하면 return
        return findVerifiedCoffee(coffee.getCoffeeId());
    }
    // ★★★★★★★★★ Pagination ★★★★★★★★★
    // PageRequest => 기본적인 Pageable의 구현체
    public Page<Coffee> findCoffees(int page, int size) {
        // Page는 (page, szie)를 받아야함.
        // 뒤에 Sort.by("coffeeId").descending은 coffeeId를 기준으로 내림차순 정렬을 의미한다.
        return coffeeRepository.findAll(PageRequest.of(page, size, Sort.by("coffeeId").descending()));
    }

    // delete는 Return값이 필요없다. 그렇기 때문에 void를 사용한다.
    // 매개변수로 coffeeId를 받는 이유는 Repository에서 정확한 대상을 삭제하기 위해서이다.
    public void deleteCoffee(long coffeeId) {
        // 지우려는 Coffee가 있는지 확인 후 삭제 해야함.
        Coffee coffee = findVerifiedCoffee(coffeeId);
        coffeeRepository.delete(coffee);
    }

    //공통 기능 해당 커피가 있는지 확인
    public Coffee findVerifiedCoffee(long coffeeId) {
        Optional<Coffee> optionalCoffee =
                // Repository에서 coffeeId에 해당하는 Coffee가 있는지 조회
                coffeeRepository.findById(coffeeId);
        Coffee findCoffee =
                // 찾는 coffee가 없다면 예외 던지기
                optionalCoffee.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));
        // 찾는 coffee가 있다면 찾은 coffee 반환
        return findCoffee;
    }
    // 공통기능 중복 검증
    public void verifyExistsCoffee(String coffeeCode) {
        // Repository에서 coffeeCode에 해당하는 커피가 있는지 확인
        Optional<Coffee> coffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        // 중복되는 coffeeCode가 있다면 예외 던지기.
    if (coffee.isPresent())
        throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
