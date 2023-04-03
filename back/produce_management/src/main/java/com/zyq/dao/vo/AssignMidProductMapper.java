package com.zyq.dao.vo;

import com.zyq.domain.vo.AssignMidProduct;
import com.zyq.domain.vo.AssignProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className AssignMidProductMapper
 * @date 2023-03-01 16:28
 */
@Mapper
public interface AssignMidProductMapper {
    @Select("select assign_ID as id, assign_orderid as orderid, assign_prev as prev , assign_type as unit,assign_materialid as materialID, assign_number as number, assign_accdate as accdate, assign_date as date,assign_start as start, assign_complete as complete, midmaterial_id as midid,midmaterial_name as name,midmaterial_matenum as matenum, midmaterial_scale as scale, assign_matecomplete as matecomplete,assign_procomplete as procomplete from produce_assign a join produce_midmaterial b on(a.assign_materialid=b.midmaterial_id) where assign_ID=#{assignid}")
    List<AssignMidProduct> getassignProductByAssignid(int assignid);

    @Select("select assign_ID as id, assign_orderid as orderid, assign_prev as prev , assign_type as unit,assign_materialid as materialID, assign_number as number, assign_accdate as accdate, assign_date as date,assign_start as start, assign_complete as complete, midmaterial_id as midid,midmaterial_name as name,midmaterial_matenum as matenum, midmaterial_scale as scale, assign_matecomplete as matecomplete,assign_procomplete as procomplete from produce_assign a join produce_midmaterial b on(a.assign_materialid=b.midmaterial_id) where assign_type = #{unit}")
    List<AssignMidProduct> getassignProductByUnit(int unit);
}
