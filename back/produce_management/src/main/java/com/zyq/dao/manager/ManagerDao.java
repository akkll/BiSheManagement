package com.zyq.dao.manager;

import com.zyq.domain.manager.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ManagerDao
 * @date 2023-02-06 17:09
 */
@Mapper
public interface ManagerDao {
    @Select("select " +
            "manager_ID as `id`, manager_name as `name`, manager_iden as `iden`, maneger_cretime as `cretime`" +
            "from produce_management.produce_manager where manager_name = #{name} and manager_password = #{password}" +
            "and manager_iden = #{iden}")
    Manager getManagerByName(String name, String password, boolean iden);

}
