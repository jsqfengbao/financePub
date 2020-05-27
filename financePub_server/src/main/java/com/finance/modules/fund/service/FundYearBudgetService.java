package com.finance.modules.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.utils.PageUtils;
import com.finance.modules.fund.entity.FundYearBudgetEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 12:21:35
 */
public interface FundYearBudgetService extends IService<FundYearBudgetEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

