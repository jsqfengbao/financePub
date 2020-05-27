package com.finance.modules.my.consume.entity;

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
 * @date 2019-05-08 20:17:21
 */
@Data
@TableName("my_consume")
public class MyConsumeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 添加人ID
	 */
	private Integer sysUserId;

	/**
	 * 添加人姓名
	 */
	private String sysUserName;
	/**
	 * 消费额度
	 */
	private BigDecimal consumeMoney;
	/**
	 * 消费名称
	 */
	private String consumeName;

	/**
	 * 消费类型
	 */
	private Integer consumeType;
	/**
	 * 消费时间
	 */
	private Date consumeTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否有效
	 */
	private Integer enabled;

}
