package com.zyq.dao.employee;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyq.domain.employee.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className EmployeeMapper
 * @date 2023-02-18 16:58
 */
@Repository
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
