package com.finance.modules.my.consume.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * author jinsq
 *
 * @date 2019/5/9 19:26
 */
@Data
public class PerDayConsumeVo {

    private List<String> dayList;        //每天的日期

    private List<Integer> consumeNumList;   //当天消费笔数

    private List<BigDecimal> consumeMoneyList; //当天消费金额
}
