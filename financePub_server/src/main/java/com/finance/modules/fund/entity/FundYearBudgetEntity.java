package com.finance.modules.fund.entity;

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
 * @date 2019-07-12 12:21:35
 */
@Data
@TableName("fund_year_budget")
public class FundYearBudgetEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer yearNum;
	/**
	 * 
	 */
	private BigDecimal fundYearIncome;
	/**
	 * 
	 */
	private BigDecimal fundYearPay;
	/**
	 * 
	 */
	private Long sysUserId;
	/**
	 * 
	 */
	private String sysUserName;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
