package com.zyq.dao.vo;

import com.zyq.domain.vo.AssignOrderProductUnit;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className AssignOrderProductUnitMapper
 * @date 2023-03-12 21:01
 */
public interface AssignOrderProductUnitMapper {

    @Select("select assign_ID as assignid,assign_orderid as orderid,assign_date as date ,assign_type as unit,assign_start as assignStart,assign_matecomplete as assignMateComplete,assign_procomplete as assignProComplete,assign_complete as assignComplete,b.sales_productid as productId,b.sales_number as number,b.sales_arrange as orderArrange,b.sales_complete, c.products_name as productName,c.products_scale as product_scale,d.unitprocess_employeeid as liushui,d.unitprocess_start as unitProcessStart,d.unitprocess_complete as unitProcessComplete   from produce_assign a join produce_sales b on(b.sales_ID=a.assign_orderid) join produce_products c on(c.products_ID=b.sales_productid) left join produce_unitprocess d on(d.unitprocess_assignid=a.assign_ID) where assign_type=#{assignid} order by assign_ID")
    List<AssignOrderProductUnit> getAssignOrderProductUnit123(int assign);
}
