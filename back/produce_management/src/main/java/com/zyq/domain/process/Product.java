package com.zyq.domain.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className Produce
 * @date 2023-02-25 22:06
 */
@TableName("produce_products")
@Data
@ToString
public class Product {
    @TableId("products_ID")
    private Integer id;
    @TableField("products_name")
    private String name;
    @TableField("products_scale")
    private String scale;
    @TableField("products_mid")
    private String mid;
}
