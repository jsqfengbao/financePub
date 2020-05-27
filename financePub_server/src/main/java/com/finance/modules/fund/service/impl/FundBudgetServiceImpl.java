package com.finance.modules.fund.service.impl;

import com.finance.common.utils.PageUtils;
import com.finance.modules.fund.service.FundFamilyService;
import com.finance.modules.fund.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.common.utils.Query;

import com.finance.modules.fund.dao.FundBudgetDao;
import com.finance.modules.fund.entity.FundBudgetEntity;
import com.finance.modules.fund.service.FundBudgetService;


@Service("fundBudgetService")
public class FundBudgetServiceImpl extends ServiceImpl<FundBudgetDao, FundBudgetEntity> implements FundBudgetService {

    @Autowired
    private FundFamilyService fundFamilyService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        long sysUserId = (long) params.get("sysUserId");
        List<Long> sysUserIdList = fundFamilyService.getUserIdForCommonFamily(sysUserId);
        if(sysUserIdList.size() <=1){
            sysUserIdList.add(sysUserId);
        }
        IPage<FundBudgetEntity> page = this.page(
                new Query<FundBudgetEntity>().getPage(params),
                new QueryWrapper<FundBudgetEntity>()
                        .eq(sysUserIdList.size() == 1,"sys_user_id",sysUserId)
                        .inSql(sysUserIdList.size() > 1,"sys_user_id", ListUtil.turnListToYZ(sysUserIdList))
                .orderByDesc("create_time")
        );

        return new PageUtils(page);
    }

}