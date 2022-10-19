package com.coen.cloud.standard.service.dept;

import com.coen.cloud.standard.controller.dto.ResponseDeptDto;
import com.coen.cloud.standard.core.exception.BusinessException;
import com.coen.cloud.standard.domain.dept.Dept;

import java.util.List;

public interface DeptService {

    public List<ResponseDeptDto> deptList() throws BusinessException;
    public ResponseDeptDto deptDetail(Long deptno) throws BusinessException;
    public ResponseDeptDto deptInsert(Dept dept) throws BusinessException;
    public ResponseDeptDto deptUpdate(Dept dept) throws BusinessException;
    public void deptDelete(Long deptno) throws BusinessException;
}
