package com.coen.cloud.standard.controller.api;

import com.coen.cloud.standard.controller.dto.RequestDeptDto;
import com.coen.cloud.standard.controller.dto.ResponseDeptDto;
import com.coen.cloud.standard.core.dto.ShareDto;
import com.coen.cloud.standard.service.dept.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DeptApiController {

    final DeptService deptService;

    @GetMapping("/dept")
    public List<ResponseDeptDto> deptList() throws Exception {
        return deptService.deptList();
    }

    @GetMapping("/dept/{deptno}")
    public ResponseDeptDto deptDetail(@PathVariable Long deptno) throws Exception {
        return deptService.deptDetail(deptno);
    }

    @PostMapping("/dept")
    public ResponseDeptDto deptInsert(RequestDeptDto requestDeptDto) throws Exception {
        return deptService.deptInsert(requestDeptDto.toEntity());
    }

    @PutMapping("/dept")
    public ResponseDeptDto deptUpdate(RequestDeptDto requestDeptDto) throws Exception {
        return deptService.deptUpdate(requestDeptDto.toEntity());
    }

    @DeleteMapping("/dept/{deptno}")
    public ShareDto deptDelete(@PathVariable Long deptno) throws Exception {
        deptService.deptDelete(deptno);
        return new ShareDto(true, "삭제");
    }
}
