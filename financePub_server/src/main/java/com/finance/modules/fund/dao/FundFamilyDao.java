package com.finance.modules.fund.dao;

/**
 * author jinsq
 *
 * @date 2019/7/17 18:21
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finance.modules.fund.entity.FundFamilyEntity;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FundFamilyDao extends BaseMapper<FundFamilyEntity> {
    @Select("select a.sys_user_id from fund_family as a where a.fund_family_name = (SELECT b.fund_family_name FROM fund_family as b where b.sys_user_id = #{sysUserId})")
    List<Long> getUserIdForCommonFamily(long sysUserId);
}
