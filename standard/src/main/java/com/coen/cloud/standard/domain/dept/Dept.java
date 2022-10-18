package com.coen.cloud.standard.domain.dept;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Dept {

    @Id
    private Long deptno;

    private String dname;

    private String loc;

    @Builder
    public Dept(Long deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }
}
