package com.zyq.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyq.domain.employee.Employee;
import com.zyq.domain.vo.KaoqinEmployee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className EmployeeServiceImpl
 * @date 2023-02-18 17:16
 */

public interface EmployeeService {
    List<Employee> getEmployee(int pageno, int pagenum);
    long getEmployeeCount();
    int updateEmployee(Employee employee);
    Employee getEmployeeById(String id);
    int insertEmployee(Employee employee);
    List<KaoqinEmployee> getKaoqinEmployee();
    List<Employee> getEmployeeByUnitLiushui(int unit, int liushui);
}
