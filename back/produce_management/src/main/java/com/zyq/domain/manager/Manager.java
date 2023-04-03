package com.zyq.domain.manager;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className Manager
 * @date 2023-02-06 17:05
 */
@TableName("produce_manager")
@Data
@ToString
public class Manager {
    @TableId(value = "manager_ID", type = IdType.AUTO)
    private String id;
    @TableField("manager_name")
    private String name;
    @TableField("manager_iden")
    private int iden;
    @TableField("maneger_cretime")
    private Date createtime;
    @TableField("manager_password")
    private String password;
}
