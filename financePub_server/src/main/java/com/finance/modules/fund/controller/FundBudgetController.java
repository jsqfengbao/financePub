package com.finance.modules.fund.controller;

import java.util.Arrays;
import java.util.Map;

import com.finance.common.utils.PageUtils;
import com.finance.common.utils.R;
import com.finance.modules.fund.service.FundBudgetService;
import com.finance.modules.fund.service.FundTypeService;
import com.finance.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.modules.fund.entity.FundBudgetEntity;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-11 09:41:42
 */
@RestController
@RequestMapping("fund/fundbudget")
public class FundBudgetController extends AbstractController {
    @Autowired
    private FundBudgetService fundBudgetService;
    @Autowired
    private FundTypeService fundTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("fund:fundbudget:list")
    public R list(@RequestParam Map<String, Object> params) {
        params.put("sysUserId",getUserId());
        PageUtils page = fundBudgetService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("fund:fundbudget:info")
    public R info(@PathVariable("id") Integer id) {
        FundBudgetEntity fundBudget = fundBudgetService.getById(id);

        return R.ok().put("fundBudget", fundBudget);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("fund:fundbudget:save")
    public R save(@RequestBody FundBudgetEntity fundBudget) {
        fundBudget.setFundTypeName(fundTypeService.getById(fundBudget.getFundTypeId()).getName());
        fundBudget.setSysUserId(getUserId());
        fundBudget.setSysUserName(getUser().getUsername());
        fundBudgetService.save(fundBudget);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("fund:fundbudget:update")
    public R update(@RequestBody FundBudgetEntity fundBudget) {
        fundBudgetService.updateById(fundBudget);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("fund:fundbudget:delete")
    public R delete(@RequestBody Integer[] ids) {
        fundBudgetService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
