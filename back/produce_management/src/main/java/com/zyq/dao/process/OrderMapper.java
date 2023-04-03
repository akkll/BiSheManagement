package com.zyq.dao.process;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyq.domain.process.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className OrderMapper
 * @date 2023-02-23 16:29
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
