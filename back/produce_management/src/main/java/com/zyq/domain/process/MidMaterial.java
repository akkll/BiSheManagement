package com.zyq.domain.process;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className MidMaterial
 * @date 2023-02-26 15:47
 */
@TableName("produce_midmaterial")
@Data
@ToString
public class MidMaterial {
    @TableId(value = "midmaterial_id", type = IdType.AUTO)
    private Integer id;
    @TableField("midmaterial_name")
    private String name;
    @TableField("midmaterial_number")
    private Integer number;
    @TableField("midmaterial_scale")
    private String scale;
    @TableField("midmaterial_mate")
    private Integer mate;
    @TableField("midmaterial_matenum")
    private Float matenum;
    @TableField("midmaterial_department")
    private Integer department;
}
