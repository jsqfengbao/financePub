package com.finance.modules.my.consume.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-13 11:06:17
 */
@Data
@TableName("my_creditcard_consume")
public class MyCreditcardConsumeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String cardNo;
	/**
	 * 
	 */
	private String cardName;
	/**
	 * 
	 */
	private Integer sysUserId;
	/**
	 * 
	 */
	private String sysUserName;
	/**
	 * 
	 */
	private Date repayTime;

	/**
	 * 还款金额
	 */
	private BigDecimal repayMoney;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Integer enabled;

}
