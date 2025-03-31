package com.example.tdd_demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc

public class GreetingControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void testGreeting() throws Exception {
                MvcResult mvcResult = mockMvc
                                .perform(MockMvcRequestBuilders.get("/greeting")
                                                .accept(MediaType.APPLICATION_JSON))
                                .andReturn();

                assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus(),
                                "Validate endpoint return 200 OK status");

                String content = mvcResult.getResponse().getContentAsString();
                assertTrue(content.contains("Hello, World!"),
                                "Validate endpoint response contains Hello, World!");

                assertEquals(MediaType.APPLICATION_JSON_VALUE,
                                mvcResult.getResponse().getContentType(),
                                "Validate response is JSON");
        }

        @Test
        public void testGreetingOptionalQueryStringParam() throws Exception {
                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/greeting")
                                .accept(MediaType.APPLICATION_JSON).param("name", "User")).andReturn();

                assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus(),
                                "Validate endpoint return 200 OK status");

                assertTrue(mvcResult.getResponse().getContentAsString().contains("Hello, User!"),
                                "Validate endpoint response contains Hello, User!");

                assertEquals(MediaType.APPLICATION_JSON_VALUE,
                                mvcResult.getResponse().getContentType(),
                                "Validate response is JSON");

        }
}