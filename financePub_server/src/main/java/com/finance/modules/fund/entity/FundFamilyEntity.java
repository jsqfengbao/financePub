package com.finance.modules.fund.entity;

/**
 * author jinsq
 *
 * @date 2019/7/17 18:21
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("fund_family")
@Data
public class FundFamilyEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
            type = IdType.AUTO
    )
    private Integer id;
    private Long sysUserId;
    private String fundFamilyName;
    private Date createTime;
}

