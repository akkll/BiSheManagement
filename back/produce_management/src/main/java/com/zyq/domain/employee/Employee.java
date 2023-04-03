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
 * @className Employee
 * @date 2023-01-16 12:28
 */
@Data
@ToString
@TableName("produce_employee")
public class Employee {
    @TableId(value = "employee_ID", type = IdType.AUTO)
    private Integer id;
    @TableField("employee_name")
    private String name;
    @TableField("employee_gender")
    private int gender;
    @TableField("employee_titleID")
    private int titleID;
    @TableField("employee_ishere")
    private int ishere;
    @TableField("employee_workdate")
    private Date workdate;
    @TableField("employee_departdate")
    private Date departdate;
    @TableField("employee_card")
    private String card;
    @TableField("employee_unit")
    private String unit;
    @TableField("employee_birthday")
    private Date birthday;
    @TableField("employee_address")
    private String address;
    @TableField("employee_mobile")
    private String mobile;
    @TableField("employee_email")
    private String email;
    @TableField("employee_wechat")
    private String wechat;
    @TableField("employee_liushui")
    private Integer liushui;

}
