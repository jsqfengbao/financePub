package com.finance.modules.my.consume.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.finance.common.utils.PageUtils;
import com.finance.common.utils.Query;
import com.finance.modules.my.consume.service.MyConsumeService;
import com.finance.modules.my.consume.dao.MyConsumeDao;
import com.finance.modules.my.consume.entity.MyConsumeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("myConsumeService")
public class MyConsumeServiceImpl extends ServiceImpl<MyConsumeDao, MyConsumeEntity> implements MyConsumeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MyConsumeEntity> page = this.page(
                new Query<MyConsumeEntity>().getPage(params),
                new QueryWrapper<MyConsumeEntity>()
                .orderByDesc("consume_time")
        );

        return new PageUtils(page);
    }

    @Override
    public List<MyConsumeEntity> queryConsumeByCondition(){
        Wrapper wrapper = new QueryWrapper();
        ((QueryWrapper) wrapper).orderByAsc("consume_time");
        return this.list(wrapper);
    }

}