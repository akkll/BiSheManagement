package com.zyq.dao.vo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyq.domain.vo.MaterialgetMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className MaterialgetMaterialMapper
 * @date 2023-03-23 15:52
 */
@Mapper
public interface MaterialgetMaterialMapper extends BaseMapper<MaterialgetMaterial> {
    @Select("select materialGet_id as id,materialGet_materialid as materialid,materialGet_date as date,materialGet_unit as unit,materialGet_assignid as assignid,materialGet_number as number,b.material_name as materialname,b.material_type as materialtype,b.material_scale as materialscale from produce_materialget a join produce_materialcategory b on(b.material_ID=a.materialGet_materialid) limit #{pageno},#{pagenum}")
    List<MaterialgetMaterial> getMaterialGetMaterial(int pageno,int pagenum);
}
