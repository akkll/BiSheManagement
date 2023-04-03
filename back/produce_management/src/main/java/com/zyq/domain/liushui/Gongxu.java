package com.zyq.domain.liushui;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;



/**
 * @author MagicBOOK
 * @version 1.0
 * @className Gongxu
 * @date 2023-03-11 17:35
 */
@TableName("produce_gongxu")
@Data
@ToString
public class Gongxu {
    @TableId("gongxu_id")
    private Integer id;
    @TableField("gongxu_name")
    private String name;
    @TableField("gongxu_unit")
    private Integer unit;
}
