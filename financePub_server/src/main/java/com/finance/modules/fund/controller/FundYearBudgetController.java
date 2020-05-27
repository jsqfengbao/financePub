package com.finance.modules.fund.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.finance.common.utils.PageUtils;
import com.finance.common.utils.R;
import com.finance.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.modules.fund.entity.FundYearBudgetEntity;
import com.finance.modules.fund.service.FundYearBudgetService;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-12 12:21:35
 */
@RestController
@RequestMapping("fund/fundyearbudget")
public class FundYearBudgetController extends AbstractController {
    @Autowired
    private FundYearBudgetService fundYearBudgetService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("fund:fundyearbudget:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("sysUserId",getUserId());
        PageUtils page = fundYearBudgetService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("fund:fundyearbudget:info")
    public R info(@PathVariable("id") Integer id){
		FundYearBudgetEntity fundYearBudget = fundYearBudgetService.getById(id);

        return R.ok().put("fundYearBudget", fundYearBudget);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("fund:fundyearbudget:save")
    public R save(@RequestBody FundYearBudgetEntity fundYearBudget){
        List<FundYearBudgetEntity> fundYearBudgetList = fundYearBudgetService.list();
        for(FundYearBudgetEntity fundYearBudgetEntity : fundYearBudgetList){
            logger.info(getUserId().equals(fundYearBudgetEntity.getSysUserId())+","+(fundYearBudget.getYearNum().equals(fundYearBudgetEntity.getYearNum())));
            if(getUserId().equals(fundYearBudgetEntity.getSysUserId()) && fundYearBudget.getYearNum().equals(fundYearBudgetEntity.getYearNum())){
                return R.error("已填写过当年预算，请勿重复填写！");
            }
        }
        fundYearBudget.setSysUserId(getUserId());
        fundYearBudget.setSysUserName(getUser().getUsername());
		fundYearBudgetService.save(fundYearBudget);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("fund:fundyearbudget:update")
    public R update(@RequestBody FundYearBudgetEntity fundYearBudget){
		fundYearBudgetService.updateById(fundYearBudget);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("fund:fundyearbudget:delete")
    public R delete(@RequestBody Integer[] ids){
		fundYearBudgetService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
