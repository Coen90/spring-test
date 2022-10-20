package com.coen.cloud.standard.controller.api;

import com.coen.cloud.standard.controller.dto.ResponseDeptDto;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@ActiveProfiles({"dev", "db-mysql"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeptApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    private String version = "v1";

    @Test
    @Order(1)
    void A001_deptInsert() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("deptno", "15");
        params.add("dname", "ACCOUNTING 회계");
        params.add("loc", "NEW YORK 뉴욕");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/" + version + "/dept").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Gson gson = new Gson();

        ResponseDeptDto responseDeptDto = gson.fromJson(content, ResponseDeptDto.class);
        Assertions.assertThat(responseDeptDto.getDeptno()).isEqualTo(15L);

    }

}