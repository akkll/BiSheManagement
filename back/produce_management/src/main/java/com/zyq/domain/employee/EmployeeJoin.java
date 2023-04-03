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
 * @className EmployeeJoin
 * @date 2023-02-24 14:44
 */
@Data
@ToString
@TableName("produce_join")
public class EmployeeJoin {
    @TableId(value = "join_ID", type = IdType.AUTO)
    private Integer id;
    @TableField("join_employeeName")
    private String name;
    @TableField("join_gender")
    private Boolean gender;//性别，1男0女
    @TableField("join_card")
    private String card;//身份证
    @TableField("join_titleid")
    private int titleid;//入职职位id
    @TableField("join_time")
    private Date joindate;//预计入职时间
    @TableField("join_unit")
    private int unit;//入职车间号
    @TableField("join_birthday")
    private Date birthday;
    @TableField("join_address")
    private String address;
    @TableField("join_mobile")
    private String mobile;
    @TableField("join_email")
    private String email;
    @TableField("join_wechat")
    private String wechat;
    @TableField("join_state")
    private Integer state;
    @TableField("join_appdate")
    private Date appdate;
    @TableField("join_liushui")
    private Integer liushui;
}
