package com.springboot.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Member {
        @Id
        private long memberId;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false, unique = true)
        private String name;

        @Column(nullable = false)
        private String phone;
}
