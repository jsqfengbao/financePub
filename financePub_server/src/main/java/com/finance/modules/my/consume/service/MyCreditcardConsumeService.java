package com.finance.modules.my.consume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.utils.PageUtils;
import com.finance.modules.my.consume.entity.MyCreditcardConsumeEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-13 11:06:17
 */
public interface MyCreditcardConsumeService extends IService<MyCreditcardConsumeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

