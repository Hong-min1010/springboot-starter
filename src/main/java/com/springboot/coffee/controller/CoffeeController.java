package com.springboot.coffee.controller;

import com.springboot.coffee.dto.CoffeePostDto;
import com.springboot.coffee.entity.Coffee;
import com.springboot.coffee.mapper.CoffeeMapper;
import com.springboot.coffee.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")

public class CoffeeController {
    private CoffeeService coffeeService;
    private CoffeeMapper coffeeMapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper coffeeMapper) {
        this.coffeeService = coffeeService;
        this.coffeeMapper = coffeeMapper;
    }

//    @PostMapping
//    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
//        Coffee coffee = coffeeService.createCoffee(coffeeMapper.coffeePostDtoToCoffee(coffeePostDto));
//    }
}
