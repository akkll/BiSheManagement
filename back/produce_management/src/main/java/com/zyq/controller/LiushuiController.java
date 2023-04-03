package com.zyq.controller;

import com.zyq.controller.utils.R;
import com.zyq.domain.employee.Employee;
import com.zyq.domain.liushui.Gongxu;
import com.zyq.domain.liushui.Liushui;
import com.zyq.service.impl.EmployeeServiceImpl;
import com.zyq.service.impl.LiushuiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className LiushuiController
 * @date 2023-03-11 17:42
 */
@RestController
@RequestMapping("liushui")
public class LiushuiController {
    @Autowired
    private LiushuiServiceImpl liushuiService;
    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/getGongxuByUnit")
    public R getGongxuByUnit(int unit){
        List<Gongxu> gongxuByUnit = liushuiService.getGongxuByUnit(unit);
        return new R(true, gongxuByUnit);
    }

    @GetMapping("/getEmployeeByUnitLiushui")
    public R getEmployeeUnitLiushui(int unit, int liushui){
        List<Employee> employeeByUnitLiushui = employeeService.getEmployeeByUnitLiushui(unit, liushui);
        return new R(true, employeeByUnitLiushui);
    }
    @GetMapping("/getLiushuiEmployee")
    public R getLiushuiEmployee(int unit, int id,int gongxu){
        Liushui liushui = liushuiService.getLiushui(unit, id, gongxu);
        return new R(true, liushui);
    }

    @PostMapping("/updateOrInsertLiushui")
    public R getUpdateOrInsertLiushui(int unit, int id,int gongxu,int employeeid){
        Boolean aBoolean = liushuiService.updateOrInsertLiushui(unit, id, gongxu, employeeid);
        return new R(true,aBoolean);
    }

}
