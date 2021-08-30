package com.lilium.testing.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setup() {
        userService = new UserService();
    }

    @Test
    void helloWorldTest() {
        String helloWorld = userService.helloWorld();

        Assertions.assertThat(helloWorld).isNotNull();
        Assertions.assertThat(helloWorld).isEqualTo("hello world");
    }
}
