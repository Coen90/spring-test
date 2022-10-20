package com.coen.cloud.standard.controller.api;

import com.coen.cloud.standard.controller.dto.ResponseDeptDto;
import com.coen.cloud.standard.core.dto.ShareDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        params.add("deptno", "25");
        params.add("dname", "ACCOUNTING 회계");
        params.add("loc", "NEW YORK 뉴욕");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/" + version + "/dept").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Gson gson = new Gson();

        ResponseDeptDto responseDeptDto = gson.fromJson(content, ResponseDeptDto.class);


//        assertThat(responseDeptDto.getDeptno()).isEqualTo(25L);
    }

    @Test
    @Order(2)
    void A002_deptUpdate() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("deptno", "25");
        params.add("dname", "ACCOUNTING 회계");
        params.add("loc", "NEW YORK 뉴욕");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/" + version + "/dept").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn();
        String contents = result.getResponse().getContentAsString();
        Gson gson = new Gson();
        ResponseDeptDto responseDeptDto = gson.fromJson(contents, ResponseDeptDto.class);
        assertThat(responseDeptDto.getDeptno()).isEqualTo(25L);
    }

    @Test
    @Order(3)
    void A003_deptList() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/" + version + "/dept"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn();
        String contents = result.getResponse().getContentAsString();
        Gson gson = new Gson();
        List<ResponseDeptDto> responseDeptDtoList = gson.fromJson(contents, new TypeToken<List<ResponseDeptDto>>(){}.getType());
        responseDeptDtoList.forEach(dept -> {
            log.debug(dept.toString());
        });
    }

    @Test
    @Order(4)
    void A004_dept() throws Exception {
        Long deptno = 25L;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/" + version + "/dept/" + deptno))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Gson gson = new Gson();
        ResponseDeptDto responseDeptDto = gson.fromJson(contentAsString, ResponseDeptDto.class);
        assertThat(responseDeptDto.getDeptno()).isEqualTo(deptno);
    }

    @Test
    @Order(5)
    void A005_dept() throws Exception {
        Long deptno = 25L;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/" + version + "/dept/" + deptno))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Gson gson = new Gson();
        ShareDto shareDto = gson.fromJson(contentAsString, ShareDto.class);
        log.debug(shareDto.toString());
    }

}