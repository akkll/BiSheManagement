package com.zyq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zyq.dao.employee.EmployeeJoinMapper;
import com.zyq.dao.employee.EmployeeMapper;
import com.zyq.domain.employee.Employee;
import com.zyq.domain.employee.EmployeeDepart;
import com.zyq.domain.employee.EmployeeJoin;
import com.zyq.service.EmployeeJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className EmployeeJoinImpl
 * @date 2023-02-24 15:37
 */
@Service
public class EmployeeJoinServiceImpl implements EmployeeJoinService {

    @Autowired
    private EmployeeJoinMapper employeeJoinMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int updateStateByID(String id) {
        LambdaUpdateWrapper<EmployeeJoin> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(EmployeeJoin::getState, 1)
                .eq(EmployeeJoin::getId, id);
        employeeJoinMapper.update(null, updateWrapper);
        LambdaQueryWrapper<EmployeeJoin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(EmployeeJoin::getId, id);
        EmployeeJoin employeeJoin = employeeJoinMapper.selectOne(queryWrapper);
        Employee employee = new Employee();
        employee.setName(employeeJoin.getName());
        employee.setGender(employeeJoin.getGender()==true?1:0);
        employee.setTitleID(employeeJoin.getTitleid());
        employee.setIshere(1);
        employee.setWorkdate(employeeJoin.getJoindate());
        employee.setCard(employeeJoin.getCard());
        employee.setUnit(String.valueOf(employeeJoin.getUnit()));
        employee.setBirthday(employeeJoin.getBirthday());
        employee.setAddress(employeeJoin.getAddress());
        employee.setMobile(employeeJoin.getMobile());
        employee.setEmail(employeeJoin.getEmail());
        employee.setWechat(employeeJoin.getWechat());
        employee.setLiushui(employeeJoin.getLiushui());
        int insert = employeeMapper.insert(employee);
        return insert;
    }

    @Override
    public int insertApp(EmployeeJoin employeeJoin) {
        int insert = employeeJoinMapper.insert(employeeJoin);
        employeeJoin.setState(0);
        return insert;
    }

    @Override
    public List<EmployeeJoin> getEmployeeJoin() {
        LambdaQueryWrapper<EmployeeJoin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmployeeJoin::getState, 0);
        List<EmployeeJoin> list = employeeJoinMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public EmployeeJoin getEmployeeJoinById(String id) {
        LambdaQueryWrapper<EmployeeJoin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(EmployeeJoin::getId, id);
        EmployeeJoin employeeJoin = employeeJoinMapper.selectOne(queryWrapper);
        return employeeJoin;
    }
}
