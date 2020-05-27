package com.finance.modules.my.consume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.utils.PageUtils;
import com.finance.modules.my.consume.entity.MyConsumeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-08 20:17:21
 */
public interface MyConsumeService extends IService<MyConsumeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public List<MyConsumeEntity> queryConsumeByCondition();
}

