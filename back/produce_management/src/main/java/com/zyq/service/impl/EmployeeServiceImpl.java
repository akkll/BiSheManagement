package com.zyq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyq.dao.employee.EmployeeMapper;
import com.zyq.dao.vo.KaoqinEmployeeMapper;
import com.zyq.domain.employee.Employee;
import com.zyq.domain.vo.KaoqinEmployee;
import com.zyq.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className EmployeeService
 * @date 2023-02-18 17:04
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private KaoqinEmployeeMapper kaoqinEmployeeMapper;

    public List<Employee> getEmployee(int pageno, int pagenum){
        Page<Employee> page = new Page<>(pageno, pagenum);
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        employeeMapper.selectPage(page, wrapper);
        return page.getRecords();
    }

    @Override
    public long getEmployeeCount() {
        Page<Employee> page = new Page<>();
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        employeeMapper.selectPage(page, wrapper);
        return page.getTotal();
    }

    @Override
    public int updateEmployee(Employee employee) {
        LambdaUpdateWrapper<Employee> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(Employee::getName, employee.getName())
                .set(Employee::getGender, employee.getGender())
                .set(Employee::getTitleID, employee.getTitleID())
                .set(Employee::getIshere, employee.getIshere())
                .set(Employee::getWorkdate, employee.getWorkdate())
                .set(Employee::getDepartdate, employee.getDepartdate())
                .set(Employee::getCard, employee.getCard())
                .set(Employee::getUnit, employee.getUnit())
                .set(Employee::getBirthday, employee.getBirthday())
                .set(Employee::getAddress, employee.getAddress())
                .set(Employee::getMobile, employee.getMobile())
                .set(Employee::getEmail, employee.getEmail())
                .set(Employee::getWechat, employee.getWechat())
                .eq(Employee::getId, employee.getId());
        int update = employeeMapper.update(null, updateWrapper);
        return update;
    }

    @Override
    public Employee getEmployeeById(String id) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getId, id);
        Employee employee = employeeMapper.selectOne(queryWrapper);
        return employee;
    }

    @Override
    public int insertEmployee(Employee employee) {
        int insert = employeeMapper.insert(employee);
        return insert;
    }

    @Override
    public List<KaoqinEmployee> getKaoqinEmployee() {
        List<KaoqinEmployee> kaoqinEmployee = kaoqinEmployeeMapper.getKaoqinEmployee();
        return kaoqinEmployee;
    }

    @Override
    public List<Employee> getEmployeeByUnitLiushui(int unit, int liushui) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUnit, unit).eq(Employee::getLiushui, liushui);
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        return employees;
    }


}
