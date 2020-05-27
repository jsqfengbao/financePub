package com.finance.modules.fund.controller;

import java.util.Arrays;
import java.util.Map;

import com.finance.common.utils.PageUtils;
import com.finance.common.utils.R;
import com.finance.modules.fund.entity.FundTypeEntity;
import com.finance.modules.fund.service.FundTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-11 09:41:42
 */
@RestController
@RequestMapping("fund/fundtype")
public class FundTypeController {
    @Autowired
    private FundTypeService fundTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("fund:fundtype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fundTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("fund:fundtype:info")
    public R info(@PathVariable("id") Integer id){
		FundTypeEntity fundType = fundTypeService.getById(id);

        return R.ok().put("fundType", fundType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("fund:fundtype:save")
    public R save(@RequestBody FundTypeEntity fundType){
		fundTypeService.save(fundType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("fund:fundtype:update")
    public R update(@RequestBody FundTypeEntity fundType){
		fundTypeService.updateById(fundType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("fund:fundtype:delete")
    public R delete(@RequestBody Integer[] ids){
		fundTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
