package com.finance.modules.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.utils.PageUtils;
import com.finance.modules.fund.entity.FundIncomeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-11 09:41:42
 */
public interface FundIncomeService extends IService<FundIncomeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public List<FundIncomeEntity> getThisYearIncome(long sysUserId, int yearNum);

    public List<FundIncomeEntity> getThisMonthIncomeHead5(long sysUserId, int yearNum, int monthNum);
}

