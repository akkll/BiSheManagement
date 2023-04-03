package com.zyq.service.unti;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zyq.domain.employee.Employee;

import java.sql.Wrapper;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className EmployeeUtils
 * @date 2023-02-19 14:35
 */
public class EmployeeUtils {
    public static LambdaQueryWrapper filterQueryWrapper(Employee employee){
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();

        return queryWrapper;
    }
}
