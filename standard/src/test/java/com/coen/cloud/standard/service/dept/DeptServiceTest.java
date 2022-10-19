package com.coen.cloud.standard.service.dept;

import com.coen.cloud.standard.controller.dto.ResponseDeptDto;
import com.coen.cloud.standard.domain.dept.Dept;
import com.coen.cloud.standard.domain.dept.DeptRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ActiveProfiles({"dev", "db-mysql"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeptServiceTest {

    @Autowired
    DeptService deptService;

    @Autowired
    DeptRepository deptRepository;

    @Test
    @Order(1)
    @Commit
    @Transactional
    void A001_DEPT_SERVICE_INSERT() {
        List<Dept> deptList = new ArrayList<>();
        deptList.add(Dept.builder().deptno(10L).dname("ACCOUNTING").loc("NEW YORK").build());
        deptList.add(Dept.builder().deptno(20L).dname("RESEARCH").loc("DALLAS").build());
        deptList.add(Dept.builder().deptno(30L).dname("SALES").loc("CHICAGO").build());
        deptList.add(Dept.builder().deptno(40L).dname("OPERATIONS").loc("BOSTON").build());

        for(Dept dept: deptList) {
            deptService.deptInsert(dept);
        }

        for(Dept dept: deptList) {
            Long deptno = dept.getDeptno();
            ResponseDeptDto responseDeptDto = deptService.deptDetail(deptno);
            assertThat(responseDeptDto.getDeptno()).isEqualTo(deptno);
        }
    }

    @Test
    @Order(2)
    @Commit
    @Transactional
    void A001_DEPT_SERVICE_UPDATE() {
        String changeDname = "ACCOUNTING2";
        deptService.deptUpdate(Dept.builder().deptno(10L).dname(changeDname).loc("NEW YORK").build());
        ResponseDeptDto responseDeptDto = deptService.deptDetail(10L);
        log.debug("responseDeptDto.toString()={}", responseDeptDto.toString());
        assertThat(responseDeptDto.getDname()).isEqualTo(changeDname);
    }

    @Test
    @Order(3)
    @Commit
    @Transactional
    void A001_DEPT_SERVICE_DELETE() {
        Long[] deptnos = { 10L, 20L, 30L, 40L };
        for (Long deptno : deptnos) {
            deptService.deptDelete(deptno);

            boolean isPresent = deptRepository.findById(deptno).isPresent();
            log.debug("String.valueOf(isPresent)={}", String.valueOf(isPresent));
            assertThat(isPresent).isFalse();
        }
    }

}
