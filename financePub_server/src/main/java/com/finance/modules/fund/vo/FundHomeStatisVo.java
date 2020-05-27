package com.finance.modules.fund.vo;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * author jinsq
 *
 * @date 2019/7/12 16:49
 */
@Data
public class FundHomeStatisVo {

    private List<String> thisMonthDayName; //当月的日期
    private List<String> thisYearMonthName; //当年的月份名

    private List<String> thisMonthPayDayName; //当月支出日期
    private JSONArray thisMonthPayDayData; //当月支出数据

    private List<String> thisMonthIncomeDayName; //当月收入日期
    private JSONArray thisMonthIncomeDayData; //当月收入数据

    private List<String> thisMonthPayTypeName; //当月支出类别名
    private JSONArray thisMonthPayTypeData; //当月支出类别数据

    private List<String> thisMonthIncomeTypeName; //当月收入类别名
    private JSONArray thisMonthIncomeTypeData; //当月收入类别数据

    private List<String> thisYearPayMonthName; //当年支出月份名
    private JSONArray thisYearPayMonthData; //当年支出月份数据

    private List<String> thisYearIncomeMonthName; //当年收入月份数据
    private JSONArray thisYearIncomeMonthData; //当年收入月份数据

    private List<String> thisMonthPayBudgetTypeName; //当月支出预算类别名
    private JSONArray thisMonthPayBudgetTypeData; //当月支出预算类别数据

    private BigDecimal thisYearTotalBudgetIncomeMoney; //当年收入预算总数
    private BigDecimal thisYearTotalBudgetPayMoney; //当年支出预算总数
    private BigDecimal thisYearTotalIncomeMoney; //当年收入总数
    private BigDecimal thisYearTotalPayMoney; //当年支出总数

    private String thisYearTotalIncomeBudgetRatio; //当年收入完成预算的百分比
    private String thisYearTotalPayBudgetRatio; //当年支出完成预算的百分比
    private JSONArray thisYearPayIncomeRatio; //当年每月消费占收入的比率

    private JSONArray thisMonthPayHead5Data; //当月支出前五项数据
    private JSONArray thisMonthIncomeHead5Data; //当月收入前五项数据
}
