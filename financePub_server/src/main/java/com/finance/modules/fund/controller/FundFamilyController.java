package com.finance.modules.fund.controller;

import com.finance.common.utils.PageUtils;
import com.finance.common.utils.R;
import com.finance.modules.fund.entity.FundFamilyEntity;
import com.finance.modules.fund.service.FundFamilyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * author jinsq
 *
 * @date 2019/7/17 18:20
 */

@RestController
@RequestMapping({"fund/fundfamily"})
public class FundFamilyController {
    @Autowired
    private FundFamilyService fundFamilyService;

    public FundFamilyController() {
    }

    @RequestMapping({"/list"})
    @RequiresPermissions({"fund:fundfamily:list"})
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = this.fundFamilyService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping({"/info/{id}"})
    @RequiresPermissions({"fund:fundfamily:info"})
    public R info(@PathVariable("id") Integer id) {
        FundFamilyEntity fundFamily = (FundFamilyEntity)this.fundFamilyService.getById(id);
        return R.ok().put("fundFamily", fundFamily);
    }

    @RequestMapping({"/save"})
    @RequiresPermissions({"fund:fundfamily:save"})
    public R save(@RequestBody FundFamilyEntity fundFamily) {
        this.fundFamilyService.save(fundFamily);
        return R.ok();
    }

    @RequestMapping({"/update"})
    @RequiresPermissions({"fund:fundfamily:update"})
    public R update(@RequestBody FundFamilyEntity fundFamily) {
        this.fundFamilyService.updateById(fundFamily);
        return R.ok();
    }

    @RequestMapping({"/delete"})
    @RequiresPermissions({"fund:fundfamily:delete"})
    public R delete(@RequestBody Integer[] ids) {
        this.fundFamilyService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}

