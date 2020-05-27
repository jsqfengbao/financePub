package com.finance.modules.my.consume.service.impl;

import com.finance.common.utils.PageUtils;
import com.finance.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.finance.modules.my.consume.dao.MyCreditcardConsumeDao;
import com.finance.modules.my.consume.entity.MyCreditcardConsumeEntity;
import com.finance.modules.my.consume.service.MyCreditcardConsumeService;


@Service("myCreditcardConsumeService")
public class MyCreditcardConsumeServiceImpl extends ServiceImpl<MyCreditcardConsumeDao, MyCreditcardConsumeEntity> implements MyCreditcardConsumeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MyCreditcardConsumeEntity> page = this.page(
                new Query<MyCreditcardConsumeEntity>().getPage(params),
                new QueryWrapper<MyCreditcardConsumeEntity>()
        );

        return new PageUtils(page);
    }

}