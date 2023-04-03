package com.zyq.dao.vo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyq.domain.vo.AssignProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className AssignProductMapper
 * @date 2023-02-28 15:54
 */
@Mapper
public interface AssignProductMapper extends BaseMapper<AssignProduct> {
    @Select("select assign_ID as id,assign_orderid as orderid, assign_prev as prev,assign_materialid as materialID, assign_number as number ,  assign_accdate as accdate, assign_date as date, assign_start as start, assign_complete as complete,products_ID as productid,products_name as name,products_scale as scale,products_mid as mid, assign_matecomplete as matecomplete,assign_procomplete as procomplete  from produce_assign a join produce_products b on(b.products_ID = a.assign_materialid) where assign_ID = #{assignid}")
    List<AssignProduct> getassignProductByAssignid(int assignid);
    @Select("select assign_ID as id,assign_orderid as orderid, assign_prev as prev,assign_materialid as materialID, assign_number as number ,  assign_accdate as accdate, assign_date as date, assign_start as start, assign_complete as complete,products_ID as productid,products_name as name,products_scale as scale,products_mid as mid, assign_matecomplete as matecomplete,assign_procomplete as procomplete  from produce_assign a join produce_products b on(b.products_ID = a.assign_materialid) where assign_type = #{unit}")
    List<AssignProduct> getassignProductByUnit(int unit);
}
