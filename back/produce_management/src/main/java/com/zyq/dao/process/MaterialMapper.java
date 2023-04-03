package com.zyq.dao.process;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyq.domain.process.Material;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className MaterialMapper
 * @date 2023-02-28 15:16
 */
@Mapper
public interface MaterialMapper extends BaseMapper<Material> {
}
