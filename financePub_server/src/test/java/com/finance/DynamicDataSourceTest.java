package com.finance;

import com.finance.modules.sys.entity.SysUserEntity;
import com.finance.service.DataSourceTestService;
import com.finance.service.DynamicDataSourceTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 多数据源测试
 *
 * @author Mark sunlightcs@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    private DynamicDataSourceTestService dynamicDataSourceTestService;
    @Autowired
    private DataSourceTestService dataSourceTestService;

    @Test
    public void test(){
        Long id = 1L;

        dynamicDataSourceTestService.updateUser(id);
        dynamicDataSourceTestService.updateUserBySlave1(id);
        dynamicDataSourceTestService.updateUserBySlave2(id);
    }

    @Test
    public void testQueryUser(){
        long id = 2;
        SysUserEntity sysUserEntity = dataSourceTestService.queryUser(id);
        System.out.println(sysUserEntity.getUsername());
    }
    @Test
    public void testQueryUser2(){
        long id = 2;
        SysUserEntity sysUserEntity = dataSourceTestService.queryUser2(id);
        System.out.println(sysUserEntity.getUsername());
    }


}
