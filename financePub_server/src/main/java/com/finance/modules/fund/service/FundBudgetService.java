package com.finance.modules.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.utils.PageUtils;
import com.finance.modules.fund.entity.FundBudgetEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-11 09:41:42
 */
public interface FundBudgetService extends IService<FundBudgetEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

