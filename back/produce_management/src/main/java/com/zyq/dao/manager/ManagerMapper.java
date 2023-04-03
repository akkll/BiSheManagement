package com.zyq.dao.manager;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyq.domain.manager.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ManagerMapper
 * @date 2023-02-25 16:56
 */

@Mapper
@Repository
public interface ManagerMapper extends BaseMapper<Manager> {
}
