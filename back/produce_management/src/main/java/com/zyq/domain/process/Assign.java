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
 * @className Assign
 * @date 2023-02-26 22:40
 */
@Data
@ToString
@TableName("produce_assign")
public class Assign {
    @TableId(value = "assign_ID", type = IdType.AUTO)
    private Integer id;
    @TableField("assign_type")
    private Integer type;
    @TableField("assign_materialid")
    private Integer materialID;
    @TableField("assign_prev")
    private Integer prev;
    @TableField("assign_accdate")
    private Date accdate;
    @TableField("assign_date")
    private Date date;
    @TableField("assign_complete")
    private Boolean complete;
    @TableField("assign_number")
    private float number;
    @TableField("assign_start")
    private Boolean start;
    @TableField("assign_orderid")
    private Integer orderid;
    @TableField("assign_matecomplete")
    private Boolean matecomplete;
    @TableField("assign_procomplete")
    private Boolean procomplete;
}
