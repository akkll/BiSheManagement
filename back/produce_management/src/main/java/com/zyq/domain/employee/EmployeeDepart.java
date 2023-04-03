package com.zyq.domain.employee;

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
 * @className EmployeeDepart
 * @date 2023-02-22 14:43
 */
@Data
@ToString
@TableName("produce_depart")
public class EmployeeDepart {
    @TableId(value = "depart_ID", type = IdType.AUTO)
    private Integer departID;
    @TableField("depart_employeeID")
    private String employeeID;
    @TableField("depart_employeeName")
    private String employeename;
    @TableField("depart_apptime")
    private Date apptime;
    @TableField("depart_time")
    private Date time;
    @TableField("depart_type")
    private Integer type;
    @TableField("depart_state")
    private Integer state;
}
