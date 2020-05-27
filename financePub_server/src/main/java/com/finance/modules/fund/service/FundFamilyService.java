package com.finance.modules.fund.service;

/**
 * author jinsq
 *
 * @date 2019/7/17 18:22
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.utils.PageUtils;
import com.finance.modules.fund.entity.FundFamilyEntity;

import java.util.List;
import java.util.Map;

public interface FundFamilyService extends IService<FundFamilyEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<Long> getUserIdForCommonFamily(long sysUserId);
}
