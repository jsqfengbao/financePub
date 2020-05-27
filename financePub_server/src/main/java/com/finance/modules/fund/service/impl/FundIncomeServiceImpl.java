package com.finance.modules.fund.service.impl;

import com.finance.common.utils.PageUtils;
import com.finance.common.utils.Query;
import com.finance.modules.fund.dao.FundIncomeDao;
import com.finance.modules.fund.service.FundFamilyService;
import com.finance.modules.fund.service.FundIncomeService;
import com.finance.modules.fund.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.finance.modules.fund.entity.FundIncomeEntity;


@Service("fundIncomeService")
public class FundIncomeServiceImpl extends ServiceImpl<FundIncomeDao, FundIncomeEntity> implements FundIncomeService {

    @Autowired
    private FundIncomeDao fundIncomeDao;
    @Autowired
    private FundFamilyService fundFamilyService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        long sysUserId = (long) params.get("sysUserId");
        List<Long> sysUserIdList = fundFamilyService.getUserIdForCommonFamily(sysUserId);
        if(sysUserIdList.size() <=1){
            sysUserIdList.add(sysUserId);
        }
        IPage<FundIncomeEntity> page = this.page(
                new Query<FundIncomeEntity>().getPage(params),
                new QueryWrapper<FundIncomeEntity>()
                    .eq(sysUserIdList.size() == 1,"sys_user_id",sysUserId)
                    .inSql(sysUserIdList.size() > 1,"sys_user_id", ListUtil.turnListToYZ(sysUserIdList))
                    .eq("enabled",1)
                    .orderByDesc("income_time")
        );

        return new PageUtils(page);
    }

    @Override
    public List<FundIncomeEntity> getThisYearIncome(long sysUserId, int yearNum){
        List<Long> sysUserIdList = fundFamilyService.getUserIdForCommonFamily(sysUserId);
        if(sysUserIdList.size() >= 1){
            return fundIncomeDao.getThisYearIncome(sysUserIdList,yearNum);
        }else{
            sysUserIdList = new ArrayList<>();
            sysUserIdList.add(sysUserId);
            return fundIncomeDao.getThisYearIncome(sysUserIdList,yearNum);
        }
    }

    @Override
    public List<FundIncomeEntity> getThisMonthIncomeHead5(long sysUserId, int yearNum, int monthNum){
        List<Long> sysUserIdList = fundFamilyService.getUserIdForCommonFamily(sysUserId);
        if(sysUserIdList.size() >= 1){
            return fundIncomeDao.getThisMonthIncomeHead5(sysUserIdList,yearNum,monthNum);
        }else{
            sysUserIdList = new ArrayList<>();
            sysUserIdList.add(sysUserId);
            return fundIncomeDao.getThisMonthIncomeHead5(sysUserIdList,yearNum,monthNum);
        }
    }
}