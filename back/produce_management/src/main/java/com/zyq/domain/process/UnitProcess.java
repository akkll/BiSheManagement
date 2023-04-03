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
 * @className UnitProcess
 * @date 2023-03-02 14:29
 */
@Data
@ToString
@TableName("produce_unitprocess")
public class UnitProcess {
    @TableId(value = "unitprocess_id", type = IdType.AUTO)
    private Integer id;
    @TableField("unitprocess_unit")
    private Integer unit;
    @TableField("unitprocess_employeeid")
    private Integer employeeid;
    @TableField("unitprocess_assignid")
    private Integer assignid;
    @TableField("unitprocess_accdate")
    private Date accdate;
    @TableField("unitprocess_date")
    private Date date;
    @TableField("unitprocess_complete")
    private Boolean complete;
    @TableField("unitprocess_start")
    private Boolean start;
}
