package com.finance.modules.fund.service.impl;

import com.finance.common.utils.PageUtils;
import com.finance.modules.fund.dao.FundPayDao;
import com.finance.modules.fund.service.FundFamilyService;
import com.finance.modules.fund.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.common.utils.Query;

import com.finance.modules.fund.entity.FundPayEntity;
import com.finance.modules.fund.service.FundPayService;


@Service("fundPayService")
public class FundPayServiceImpl extends ServiceImpl<FundPayDao, FundPayEntity> implements FundPayService {

    @Autowired
    private FundPayDao fundPayDao;
    @Autowired
    private FundFamilyService fundFamilyService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        long sysUserId = (long) params.get("sysUserId");
        List<Long> sysUserIdList = fundFamilyService.getUserIdForCommonFamily(sysUserId);
        if(sysUserIdList.size() <=1){
            sysUserIdList.add(sysUserId);
        }
        IPage<FundPayEntity> page = this.page(
                new Query<FundPayEntity>().getPage(params),
                new QueryWrapper<FundPayEntity>()
                        .eq(sysUserIdList.size() == 1,"sys_user_id",sysUserId)
                        .inSql(sysUserIdList.size() > 1,"sys_user_id", ListUtil.turnListToYZ(sysUserIdList))
                        .orderByDesc("pay_time")
        );

        return new PageUtils(page);
    }

    @Override
    public List<FundPayEntity> getThisYearPay(long sysUserId,int yearNum){
        List<Long> sysUserIdList = fundFamilyService.getUserIdForCommonFamily(sysUserId);
        if(sysUserIdList.size() >= 1){
            return fundPayDao.getThisYearPay(sysUserIdList,yearNum);
        }else{
            sysUserIdList = new ArrayList<>();
            sysUserIdList.add(sysUserId);
            return fundPayDao.getThisYearPay(sysUserIdList,yearNum);
        }
    }

    @Override
    public List<FundPayEntity> getThisMonthPayHead5(long sysUserId, int yearNum, int monthNum){
        List<Long> sysUserIdList = fundFamilyService.getUserIdForCommonFamily(sysUserId);
        if(sysUserIdList.size() >= 1){
            return fundPayDao.getThisMonthPayHead5(sysUserIdList,yearNum,monthNum);
        }else{
            sysUserIdList = new ArrayList<>();
            sysUserIdList.add(sysUserId);
            return fundPayDao.getThisMonthPayHead5(sysUserIdList,yearNum,monthNum);
        }
    }

}