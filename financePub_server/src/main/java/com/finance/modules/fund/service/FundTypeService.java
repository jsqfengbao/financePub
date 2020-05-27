package com.finance.modules.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.utils.PageUtils;
import com.finance.modules.fund.entity.FundTypeEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-11 09:41:42
 */
public interface FundTypeService extends IService<FundTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

