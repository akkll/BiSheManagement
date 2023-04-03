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
 * @className Order
 * @date 2023-02-23 16:19
 */
@Data
@ToString
@TableName("produce_sales")
public class Order {
    @TableId(value = "sales_ID", type = IdType.AUTO)
    private Integer id;
    @TableField("sales_productid")
    private Integer produceid;
    @TableField("sales_beizhu")
    private String beizhu;
    @TableField("sales_accdate")
    private Date accdate;
    @TableField("sales_number")
    private Integer number;
    @TableField("sales_delivery")
    private Date delivery;
    @TableField("sales_completedate")
    private Date completedate;
    @TableField("sales_arrange")
    private boolean arrange;
    @TableField("sales_complete")
    private boolean complete;
}
