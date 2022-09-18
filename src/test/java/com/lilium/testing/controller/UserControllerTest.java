package com.lilium.testing.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetUsernames() throws Exception {
        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(
                "/api/users/usernames"
        );

        mvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$")
                        .value(Matchers.containsInAnyOrder("James Bond","Frank Castle","T Pain"))
                );
    }

    @Test
    void testGetJamesBond() throws Exception {
        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(
                "/api/users/james"
        );

        mvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username")
                        .value(Matchers.is("007"))
                );
    }
}
