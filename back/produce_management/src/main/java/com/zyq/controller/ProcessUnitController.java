package com.zyq.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyq.controller.utils.R;
import com.zyq.dao.employee.EmployeeMapper;
import com.zyq.dao.process.UnitProcessMapper;
import com.zyq.domain.employee.Employee;
import com.zyq.domain.process.Assign;
import com.zyq.domain.process.UnitProcess;
import com.zyq.domain.vo.AssignMidProduct;
import com.zyq.domain.vo.AssignOrderProductUnit;
import com.zyq.domain.vo.AssignProduct;
import com.zyq.service.OrderService;
import com.zyq.service.impl.OrderServiceImpl;
import com.zyq.service.impl.ProductMaterialMidServiceImpl;
import com.zyq.service.impl.UnitProcessServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ProcessUnitController
 * @date 2023-02-28 15:09
 */
@RestController
@RequestMapping("processUnit")
public class ProcessUnitController {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ProductMaterialMidServiceImpl productMaterialMidService;
    @Autowired
    private UnitProcessServiceImpl unitProcessService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/getAssignProductByUnit")//给3，4车间使用
    public R method1(int unit){
        List<AssignProduct> assign = orderService.getAssignProductByUnit(unit);
        R r = new R(true, assign);
        return r;
    }
    @GetMapping("/getAssignMidProductByUnit")//给1，2车间使用
    public R method4(int unit){
        List<AssignMidProduct> assignMidProductByUnit = orderService.getAssignMidProductByUnit(unit);
        return new R(true, assignMidProductByUnit);
    }
    @PostMapping("/Unit4CuidanStart")
    public R method2(@RequestBody Map<String, Integer> map){
        int i = orderService.Unit4CuidanStart(map.get("assignid"));
        return new R(true, i);
    }
    @PostMapping("/Unit3CuidanStart")
    public R method3(@RequestBody Map<String, Integer> map){
        int i = orderService.Unit3CuidanStart(map.get("assignid"));
        return new R(true, i);
    }
    @PostMapping("/Unit12GetMaterialComplete")
    public R method5(@RequestBody Map<String, Integer> map){
        Float i = orderService.Unit12GetMaterialComplete(map.get("assignid"));
        return new R(true, i);
    }
    /*
    * 以下是给各个车间内部调用的
    */
    @GetMapping("/getUnitProcessByUnit")
    public R method6(int unit){
        List<UnitProcess> process = unitProcessService.getUnitProcessByUnit(unit);
        return new R(true, process);
    }
    @GetMapping("/getUnitEmployee")
    public R method7(int unit){
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUnit, unit);
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        return new R(true, employees);
    }
    @PostMapping("/addEmployeeUnitProcess")
    public R method8(@RequestBody Map<String, Integer> map){
        Integer id = map.get("id");
        Integer employeeid = map.get("employeeid");
        int i = unitProcessService.addEmployeeUnitProcess(employeeid, id);
        return new R(true, i);
    }
    @PostMapping("/completeUnitProcess")
    public R method9(@RequestBody Map<String, Integer> map){
        Integer assignid = map.get("assignid");
        Integer id = map.get("id");
        int i = unitProcessService.completeUnitProcess(id, assignid);
        return new R(true, i);
    }
    @GetMapping("/getAssignUnit123")
    public R getAssignByUnitComplete(int unit){
        List<AssignOrderProductUnit> list = unitProcessService.getAssignOrderProductUnit123(unit);
        return new R(true,list);
    }
}
