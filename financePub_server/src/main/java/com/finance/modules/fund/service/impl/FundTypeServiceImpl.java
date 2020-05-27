package com.finance.modules.fund.service.impl;

import com.finance.common.utils.PageUtils;
import com.finance.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.common.utils.Query;

import com.finance.modules.fund.dao.FundTypeDao;
import com.finance.modules.fund.entity.FundTypeEntity;
import com.finance.modules.fund.service.FundTypeService;


@Service("fundTypeService")
public class FundTypeServiceImpl extends ServiceImpl<FundTypeDao, FundTypeEntity> implements FundTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String type = (String) params.get("type");
        IPage<FundTypeEntity> page = this.page(
                new Query<FundTypeEntity>().getPage(params),
                new QueryWrapper<FundTypeEntity>()
                .eq(StringUtils.isNotBlank(type),"type",type)
        );

        return new PageUtils(page);
    }

}