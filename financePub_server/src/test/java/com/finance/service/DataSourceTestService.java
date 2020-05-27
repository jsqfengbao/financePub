package com.finance.service;

import com.finance.modules.sys.entity.SysUserEntity;
import com.finance.datasource.annotation.DataSource;
import com.finance.datasource.properties.DataSourceNames;
import com.finance.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author jinsq
 *
 * @date 2019/3/6 12:46
 */
@Service
public class DataSourceTestService {
    @Autowired
    private SysUserService sysUserService;

    @DataSource(DataSourceNames.FIRST)
    public SysUserEntity queryUser(Long userId){
        return sysUserService.getById(userId);
    }

    @DataSource(DataSourceNames.SECOND)
    public SysUserEntity queryUser2(Long userId){
        return sysUserService.getById(userId);
    }
}
