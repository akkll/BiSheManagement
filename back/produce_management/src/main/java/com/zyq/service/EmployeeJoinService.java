package com.zyq.service;

import com.zyq.domain.employee.EmployeeDepart;
import com.zyq.domain.employee.EmployeeJoin;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className EmployeeJoinService
 * @date 2023-02-24 15:37
 */
public interface EmployeeJoinService {
    int updateStateByID(String id);
    int insertApp(EmployeeJoin EmployeeJoin);
    List<EmployeeJoin> getEmployeeJoin();
    EmployeeJoin getEmployeeJoinById(String id);
}
