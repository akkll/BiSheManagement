package com.zyq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zyq.dao.employee.EmployeeDepartMapper;
import com.zyq.dao.employee.EmployeeMapper;
import com.zyq.domain.employee.Employee;
import com.zyq.domain.employee.EmployeeDepart;
import com.zyq.service.EmployeeDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className EmployeeDepartServiceImpl
 * @date 2023-02-22 14:49
 */
@Service
public class EmployeeDepartServiceImpl implements EmployeeDepartService {

    @Autowired
    private EmployeeDepartMapper employeeDepartMapper;
    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public int updateStateByID(String id) {
        LambdaUpdateWrapper<EmployeeDepart> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(EmployeeDepart::getState, 1)
                .eq(EmployeeDepart::getEmployeeID, id);
        employeeDepartMapper.update(null, updateWrapper);
        LambdaQueryWrapper<EmployeeDepart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(EmployeeDepart::getEmployeeID, id);
        EmployeeDepart employeeDepart = employeeDepartMapper.selectOne(queryWrapper);
        LambdaUpdateWrapper<Employee> wrapper = new LambdaUpdateWrapper<>();
        wrapper
                .set(Employee::getIshere, 0)
                .set(Employee::getDepartdate, employeeDepart.getTime())
                .eq(Employee::getId, id);
        int update = employeeMapper.update(null, wrapper);
        return update;
    }

    @Override
    public int insertApp(EmployeeDepart employeeDepart) {
        int insert = employeeDepartMapper.insert(employeeDepart);
        return insert;
    }

    @Override
    public List<EmployeeDepart> getEmployeeDepart() {
        LambdaQueryWrapper<EmployeeDepart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmployeeDepart::getState, 0);
        List<EmployeeDepart> employeeDeparts = employeeDepartMapper.selectList(queryWrapper);
        return employeeDeparts;
    }
}
