package com.zyq.domain.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className Material
 * @date 2023-02-26 22:37
 */
@TableName("produce_materialCategory")
@Data
@ToString
public class Material {
    @TableId(value = "material_ID")
    private Integer id;
    @TableField("material_name")
    private String name;
    @TableField("material_type")
    private String type;
    @TableField("material_scale")
    private String scale;
    @TableField("material_number")
    private Float number;
}
