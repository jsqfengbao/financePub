package com.finance.modules.fund.dao;

import com.finance.modules.fund.entity.FundPayEntity;
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
public interface FundPayDao extends BaseMapper<FundPayEntity> {

//    @Select("select fund_type_id,fund_type_name,money,pay_type,month_num,day_num from fund_pay where enabled = 1 and sys_user_id in(#{sysUserIds}) and year_num = #{yearNum}")
    @Select({
            "<script>",
            "select fund_type_id,fund_type_name,money,pay_type,month_num,day_num from fund_pay where enabled = 1 and year_num = #{yearNum} and sys_user_id in",
            "<foreach collection='sysUserIds' item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<FundPayEntity> getThisYearPay(List<Long> sysUserIds,int yearNum);

//    @Select("select content,money from fund_pay where enabled = 1 and sys_user_id in(#{sysUserIds}) and year_num =#{yearNum} and month_num = #{monthNum} order by money desc limit 5")
    @Select({
            "<script>",
            "select content,money from fund_pay where enabled = 1 and year_num =#{yearNum} and month_num = #{monthNum} and sys_user_id in",
            "<foreach collection='sysUserIds' item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            " order by money desc limit 5",
            "</script>"
    })
    List<FundPayEntity> getThisMonthPayHead5(List<Long> sysUserIds, int yearNum, int monthNum);
}
