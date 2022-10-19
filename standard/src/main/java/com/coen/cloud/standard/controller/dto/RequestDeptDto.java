package com.coen.cloud.standard.controller.dto;

import com.coen.cloud.standard.domain.dept.Dept;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestDeptDto {

    private Long deptno;
    private String dname;
    private String loc;

    @Builder
    public RequestDeptDto(Long deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Dept toEntity() {
        return Dept.builder()
                .deptno(deptno)
                .dname(dname)
                .loc(loc)
                .build();
    }
}
