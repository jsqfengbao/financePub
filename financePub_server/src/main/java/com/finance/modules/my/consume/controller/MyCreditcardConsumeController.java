package com.finance.modules.my.consume.controller;

import java.util.Arrays;
import java.util.Map;

import com.finance.common.utils.PageUtils;
import com.finance.common.utils.R;
import com.finance.modules.sys.controller.AbstractController;
import com.finance.modules.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.modules.my.consume.entity.MyCreditcardConsumeEntity;
import com.finance.modules.my.consume.service.MyCreditcardConsumeService;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-13 11:06:17
 */
@RestController
@RequestMapping("consume/mycreditcardconsume")
public class MyCreditcardConsumeController extends AbstractController {
    @Autowired
    private MyCreditcardConsumeService myCreditcardConsumeService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("creditcard:mycreditcardconsume:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = myCreditcardConsumeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("creditcard:mycreditcardconsume:info")
    public R info(@PathVariable("id") Integer id){
		MyCreditcardConsumeEntity myCreditcardConsume = myCreditcardConsumeService.getById(id);

        return R.ok().put("myCreditcardConsume", myCreditcardConsume);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("creditcard:mycreditcardconsume:save")
    public R save(@RequestBody MyCreditcardConsumeEntity myCreditcardConsume){
        myCreditcardConsume.setSysUserId(getUserId().intValue());
        myCreditcardConsume.setSysUserName(sysUserService.getById(getUserId()).getUsername());
        myCreditcardConsume.setEnabled(1);

		myCreditcardConsumeService.save(myCreditcardConsume);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("creditcard:mycreditcardconsume:update")
    public R update(@RequestBody MyCreditcardConsumeEntity myCreditcardConsume){
		myCreditcardConsumeService.updateById(myCreditcardConsume);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("creditcard:mycreditcardconsume:delete")
    public R delete(@RequestBody Integer[] ids){
		myCreditcardConsumeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
