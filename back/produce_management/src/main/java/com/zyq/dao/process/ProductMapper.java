package com.zyq.dao.process;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyq.domain.process.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ProductMapper
 * @date 2023-02-25 22:08
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
