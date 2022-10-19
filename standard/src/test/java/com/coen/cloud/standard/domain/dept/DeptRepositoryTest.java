package com.coen.cloud.standard.domain.dept;

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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles({"dev", "db-mysql"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeptRepositoryTest {

    @Autowired
    private DeptRepository deptRepository;

    @Test
    @Order(1)
    @Commit
    void A001_DEPT_TABLE_INSERT() {
        List deptList = new ArrayList<>();
        deptList.add(Dept.builder().deptno(10L).dname("ACCOUNTING").loc("NEW YORK").build());
        deptList.add(Dept.builder().deptno(20L).dname("RESEARCH").loc("DALLAS").build());
        deptList.add(Dept.builder().deptno(30L).dname("SALES").loc("CHICAGO").build());
        deptList.add(Dept.builder().deptno(40L).dname("OPERATIONS").loc("BOSTON").build());
        deptRepository.saveAll(deptList);

        assertThat(deptRepository.findById(10L).isPresent()).isEqualTo(true);
        assertThat(deptRepository.findById(20L).isPresent()).isEqualTo(true);
        assertThat(deptRepository.findById(30L).isPresent()).isEqualTo(true);
        assertThat(deptRepository.findById(40L).isPresent()).isEqualTo(true);
    }

    @Test
    @Order(2)
    @Commit
    void A002_DEPT_TABLE_EDIT() {
        String changeDname = "ACCOUNTING2";
        deptRepository.save(Dept.builder().deptno(10L).dname(changeDname).loc("NEW YORK").build());
        Dept dept = deptRepository.findById(10L).get();
        log.debug(dept.toString());
        assertThat(changeDname).isEqualTo(dept.getDname());
    }

    @Test
    @Order(2)
    @Commit
    void A003_DEPT_TABLE_DELETE() {
        Long[] deptnos = { 10L, 20L, 30L, 40L };
        for(Long deptno: deptnos) {
            deptRepository.delete(Dept.builder().deptno(deptno).build());
            boolean isPresent = deptRepository.findById(deptno).isPresent();
            log.debug(String.valueOf(isPresent));
            assertThat(isPresent).isEqualTo(false);
        }
    }

}