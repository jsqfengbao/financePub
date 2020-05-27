package com.finance.modules.fund.dao;

import com.finance.modules.fund.entity.FundIncomeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-11 09:41:42
 */
@Mapper
public interface FundIncomeDao extends BaseMapper<FundIncomeEntity> {

//    @Select("select fund_type_id,fund_type_name,money,month_num,day_num from fund_income where enabled = 1 and sys_user_id in (#{sysUserIds}) and year_num = #{yearNum}")
    @Select({
            "<script>",
            "SELECT fund_type_id,fund_type_name,money,month_num,day_num FROM fund_income where enabled = 1 and year_num = #{yearNum} and sys_user_id in",
            "<foreach collection='sysUserIds' item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<FundIncomeEntity> getThisYearIncome(List<Long> sysUserIds, int yearNum);

//    @Select("select content,money from fund_income where enabled = 1 and sys_user_id in (#{sysUserIds}) and year_num =#{yearNum} and month_num = #{monthNum} order by money desc limit 5")
    @Select({
            "<script>",
            "select content,money from fund_income where enabled = 1 and year_num =#{yearNum} and month_num = #{monthNum} and sys_user_id in",
            "<foreach collection='sysUserIds' item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            " order by money desc limit 5",
            "</script>"
    })
    List<FundIncomeEntity> getThisMonthIncomeHead5(List<Long> sysUserIds, int yearNum, int monthNum);
}
