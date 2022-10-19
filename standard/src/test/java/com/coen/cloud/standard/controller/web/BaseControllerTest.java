package com.coen.cloud.standard.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles({"dev", "db-h2"})
@AutoConfigureMockMvc
class BaseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    void getIndex() throws Exception {
        String data = "GET DATA";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("data", data);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content).contains(data);
    }

    @Test
    @Order(2)
    void postIndex() throws Exception {
        String data = "POST DATA";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("data", data);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content).contains(data);
    }


}