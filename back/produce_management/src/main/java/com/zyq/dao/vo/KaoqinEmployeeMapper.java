package com.zyq.dao.vo;

import com.zyq.domain.vo.KaoqinEmployee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className KaoqinEmployeeMapper
 * @date 2023-03-06 20:55
 */
@Mapper
@Repository
public interface KaoqinEmployeeMapper {

    @Select("select employee_ID as id, employee_name as name, kaoqin_shangban as shangban, kaoqin_longitude as longitude, kaoqin_latitude as latitude from produce_kaoqin a join produce_employee b on(b.employee_ID = a.kaoqin_employeeID)")
    List<KaoqinEmployee> getKaoqinEmployee();
}
