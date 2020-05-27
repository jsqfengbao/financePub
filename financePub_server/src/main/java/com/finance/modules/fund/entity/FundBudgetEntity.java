package com.finance.modules.fund.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-11 09:41:42
 */
@Data
@TableName("fund_budget")
public class FundBudgetEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type= IdType.AUTO)
	private Integer id;
	/**
	 * 
	 */
	private Integer fundTypeId;
	/**
	 * 
	 */
	private String fundTypeName;
	/**
	 * 
	 */
	private BigDecimal money;
	/**
	 * 预算月份
	 */
	private Integer monthNum;

	/**
	 * 预算年份
	 */
	private Integer yearNum;

	private Long sysUserId;

	private String sysUserName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
