package com.coen.cloud.standard.controller.dto;

import com.coen.cloud.standard.core.dto.ShareDto;
import com.coen.cloud.standard.domain.dept.Dept;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ResponseDeptDto extends ShareDto  {

    private Long deptno;
    private String dname;
    private String loc;

    public ResponseDeptDto(Dept entity) {
        this.deptno = entity.getDeptno();
        this.dname = entity.getDname();
        this.loc = entity.getLoc();
    }

}
