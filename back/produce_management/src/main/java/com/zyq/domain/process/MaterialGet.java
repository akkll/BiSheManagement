package com.zyq.domain.process;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className MaterialGet
 * @date 2023-02-27 15:27
 */
@TableName("produce_materialGet")
@ToString
@Data
public class MaterialGet {
    @TableId(value = "materialGet_id", type = IdType.AUTO)
    private Integer id;
    @TableField("materialGet_materialid")
    private Integer materialid;
    @TableField("materialGet_date")
    private Date date;
    @TableField("materialGet_unit")
    private Integer unit;
    @TableField("materialGet_assignid")
    private Integer assignid;
    @TableField("materialGet_number")
    private Float number;
    @TableField("materialGet_employeeid")
    private Integer employeeid;
}
