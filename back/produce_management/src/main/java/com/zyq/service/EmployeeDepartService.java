package com.zyq.service;

import com.zyq.domain.employee.EmployeeDepart;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className EmployeeDepartService
 * @date 2023-02-22 14:47
 */
public interface EmployeeDepartService {
    int updateStateByID(String id);
    int insertApp(EmployeeDepart employeeDepart);
    List<EmployeeDepart> getEmployeeDepart();
}
