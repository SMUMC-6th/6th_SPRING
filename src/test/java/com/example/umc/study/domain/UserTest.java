package com.example.umc.study.domain;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    void setUp() {
        em.persist(User.builder().name("user1").build());
        em.persist(User.builder().name("user2").build());
    }

}