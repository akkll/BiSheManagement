package com.zyq.dao.employee;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyq.domain.employee.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className EmployeeDao
 * @date 2023-01-16 12:27
 */
@Mapper
@Repository
public interface EmployeeDao extends BaseMapper<Employee> {

    List<Employee> getEmployee(int pageno, int pagenum);
    Employee getEmployeeByID(String id);
    int insertEmployee(Employee employee);
    int deleteEmployee(String id);
    int updateEmployee(Employee employee);
}
