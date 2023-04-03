package com.zyq.dao.vo;

import com.zyq.domain.vo.UnitProcessEmployeeMaterial;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className UnitProcessEmployeeMaterialMapper
 * @date 2023-03-03 15:13
 */
public interface UnitProcessEmployeeMaterialMapper {

    @Select("select unitprocess_id as id, unitprocess_unit as unit, unitprocess_employeeid as employeeid, unitprocess_assignid as assignid, unitprocess_accdate as accdate, unitprocess_date as date, unitprocess_complete as complete, unitprocess_start as start, employee_name as employeename\n" +
            "from produce_unitprocess a join produce_employee b on(b.employee_ID=a.unitprocess_employeeid);")
    List<UnitProcessEmployeeMaterial> getUnitProcessEmployeeMaterial();
}
