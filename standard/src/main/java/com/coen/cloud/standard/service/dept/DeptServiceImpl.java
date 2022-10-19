package com.coen.cloud.standard.service.dept;

import com.coen.cloud.standard.controller.dto.ResponseDeptDto;
import com.coen.cloud.standard.core.exception.BusinessException;
import com.coen.cloud.standard.domain.dept.Dept;
import com.coen.cloud.standard.domain.dept.DeptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

    private final DeptRepository deptRepository;

    @Override
    public List<ResponseDeptDto> deptList() throws BusinessException {

        List<ResponseDeptDto> responseDeptDtoList = new ArrayList<>();

        deptRepository.findAll().forEach(dept -> {
            log.debug("dept.toString()={}", dept.toString());
            responseDeptDtoList.add(new ResponseDeptDto(dept));
        });

        return responseDeptDtoList;
    }

    @Override
    public ResponseDeptDto deptDetail(Long deptno) throws BusinessException {

        Optional<Dept> optionalDept = deptRepository.findById(deptno);
        Dept dept = optionalDept.orElseThrow(() -> new BusinessException("해당 부서가 없습니다."));

        return new ResponseDeptDto(dept);
    }

    @Override
    @Transactional
    public ResponseDeptDto deptInsert(Dept dept) throws BusinessException {

        Optional<Dept> optionalDept = deptRepository.findById(dept.getDeptno());
        log.debug("String.valueOf(optionalDept.isPresent())={}", String.valueOf(optionalDept.isPresent()));
        if(optionalDept.isPresent()) {
            throw new BusinessException("해당 부서 번호가 이미 존재 합니다.");
        }
        dept = deptRepository.save(dept);

        return new ResponseDeptDto(dept);
    }

    @Override
    @Transactional
    public ResponseDeptDto deptUpdate(Dept dept) throws BusinessException {

        Optional<Dept> optionalDept = deptRepository.findById(dept.getDeptno());
        if(!optionalDept.isPresent()) {
            throw new BusinessException("해당 부서 번호가 존재 하지 않습니다.");
        }
        dept = deptRepository.save(dept);

        return new ResponseDeptDto(dept);
    }

    @Override
    @Transactional
    public void deptDelete(Long deptno) throws BusinessException {

        deptRepository.deleteById(deptno);
    }
}
