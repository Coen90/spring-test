package com.coen.cloud.standard.controller.web;

import com.coen.cloud.standard.controller.dto.RequestDeptDto;
import com.coen.cloud.standard.controller.dto.ResponseDeptDto;
import com.coen.cloud.standard.service.dept.DeptService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;

    @GetMapping("/dept")
    public String deptList(ModelMap modelMap) throws Exception {

        modelMap.addAttribute("deptList", deptService.deptList());

        return "deptList";
    }

    @GetMapping("/dept/{deptno}")
    public String deptDetail(@PathVariable Long deptno, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("dept", deptService.deptDetail(deptno));
        return "deptDetail";
    }

    @PostMapping("/dept")
    public String deptInsert(RequestDeptDto requestDeptDto) throws  Exception {
        deptService.deptInsert(requestDeptDto.toEntity());
        return "redirect:/dept";
    }

    @PostMapping("/dept/update")
    public String deptUpdate(RequestDeptDto requestDeptDto) throws  Exception {
        deptService.deptUpdate(requestDeptDto.toEntity());
        return "redirect:/dept";
    }

    @PostMapping("/dept/delete")
    public String deptDelete(Long deptno) throws  Exception {
        deptService.deptDelete(deptno);
        return "redirect:/dept";
    }

}
