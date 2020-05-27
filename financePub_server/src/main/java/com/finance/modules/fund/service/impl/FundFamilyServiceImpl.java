package com.finance.modules.fund.service.impl;

/**
 * author jinsq
 *
 * @date 2019/7/17 18:22
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.common.utils.PageUtils;
import com.finance.modules.fund.entity.FundFamilyEntity;
import com.finance.modules.fund.service.FundFamilyService;
import com.finance.common.utils.Query;
import com.finance.modules.fund.dao.FundFamilyDao;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fundFamilyService")
public class FundFamilyServiceImpl extends ServiceImpl<FundFamilyDao, FundFamilyEntity> implements FundFamilyService {
    @Autowired
    private FundFamilyDao fundFamilyDao;

    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FundFamilyEntity> page = this.page((new Query()).getPage(params), new QueryWrapper());
        return new PageUtils(page);
    }

    public List<Long> getUserIdForCommonFamily(long sysUserId) {
        return this.fundFamilyDao.getUserIdForCommonFamily(sysUserId);
    }
}

