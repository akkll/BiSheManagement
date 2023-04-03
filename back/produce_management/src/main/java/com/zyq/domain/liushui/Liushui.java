package com.zyq.domain.liushui;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.Data;
import lombok.ToString;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className Liushui
 * @date 2023-03-11 17:35
 */
@TableName("produce_liushui")
@Data
@ToString
public class Liushui {
    @TableField("liushui_unit")
    private Integer unit;
    @TableField("liushui_id")
    private Integer lid;
    @TableField("liushui_gongxu")
    private Integer gongxu;
    @TableField("liushui_employeeid")
    private Integer employeeid;
}
