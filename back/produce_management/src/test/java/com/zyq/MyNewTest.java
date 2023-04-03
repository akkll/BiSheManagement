package com.zyq;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyq.dao.employee.EmployeeMapper;
import com.zyq.dao.process.AssignMapper;
import com.zyq.domain.employee.Employee;
import com.zyq.domain.process.Assign;
import com.zyq.service.impl.ViewServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className MyNewTest
 * @date 2023-03-07 22:15
 */
@SpringBootTest
public class MyNewTest {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private AssignMapper assignMapper;
    @Autowired
    private ViewServiceImpl viewService;

    @Test
    public void test1(){
//        List<Map<String, Object>> beiliao = viewService.getBeiliao(1);
//        System.out.println(beiliao);
//        List<Map<String, Object>> process = viewService.getProcess(1);
//        System.out.println(process);
        QueryWrapper<Assign> wrapper = new QueryWrapper<>();
        wrapper
                .select("count(*) as num, DATE_FORMAT(assign_date,'%Y-%m') as date")
                .groupBy("DATE_FORMAT(assign_date,'%Y-%m')")
                .eq("assign_type",1);
        List<Map<String, Object>> maps1 = assignMapper.selectMaps(wrapper);
        System.out.println(maps1);
    }
}
