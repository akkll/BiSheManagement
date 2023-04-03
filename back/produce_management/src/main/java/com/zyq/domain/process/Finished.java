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
 * @className Finished
 * @date 2023-03-13 16:25
 */
@Data
@ToString
@TableName("produce_finished")
public class Finished {
    @TableId(value = "finished_id",type = IdType.AUTO)
    private Integer id;
    @TableField("finished_productid")
    private Integer productid;
    @TableField("finished_number")
    private Integer num;
    @TableField("finished_orderid")
    private Integer orderid;
    @TableField("finished_date")
    private Date date;
}
