package com.finance.modules.fund.controller;

import java.util.Arrays;
import java.util.Map;

import com.finance.common.utils.DateUtil;
import com.finance.common.utils.PageUtils;
import com.finance.common.utils.R;
import com.finance.modules.fund.entity.FundTypeEntity;
import com.finance.modules.fund.service.FundTypeService;
import com.finance.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.modules.fund.entity.FundIncomeEntity;
import com.finance.modules.fund.service.FundIncomeService;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-11 09:41:42
 */
@RestController
@RequestMapping("fund/fundincome")
public class FundIncomeController extends AbstractController {
    @Autowired
    private FundIncomeService fundIncomeService;
    @Autowired
    private FundTypeService fundTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("fund:fundincome:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("sysUserId",getUserId());
        PageUtils page = fundIncomeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("fund:fundincome:info")
    public R info(@PathVariable("id") Integer id){
		FundIncomeEntity fundIncome = fundIncomeService.getById(id);

        return R.ok().put("fundIncome", fundIncome);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("fund:fundincome:save")
    public R save(@RequestBody FundIncomeEntity fundIncome){
        fundIncome.setSysUserId(getUserId());
        fundIncome.setSysUserName(getUser().getUsername());
        FundTypeEntity fundTypeEntity = fundTypeService.getById(fundIncome.getFundTypeId());
        fundIncome.setFundTypeName(fundTypeEntity.getName());
        String montehNum = DateUtil.date2Str(fundIncome.getIncomeTime(),"yyyy-MM-dd").substring(5,7);
        String yearNum = DateUtil.date2Str(fundIncome.getIncomeTime(),"yyyy-MM-dd").substring(0,4);
        String dayNum = DateUtil.date2Str(fundIncome.getIncomeTime(),"yyyy-MM-dd").substring(8,10);
        fundIncome.setYearNum(Integer.parseInt(yearNum));
        fundIncome.setMonthNum(Integer.parseInt(montehNum));
        fundIncome.setDayNum(Integer.parseInt(dayNum));
		fundIncomeService.save(fundIncome);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("fund:fundincome:update")
    public R update(@RequestBody FundIncomeEntity fundIncome){
        String montehNum = DateUtil.date2Str(fundIncome.getIncomeTime(),"yyyy-MM-dd").substring(5,7);
        String yearNum = DateUtil.date2Str(fundIncome.getIncomeTime(),"yyyy-MM-dd").substring(0,4);
        String dayNum = DateUtil.date2Str(fundIncome.getIncomeTime(),"yyyy-MM-dd").substring(8,10);
        fundIncome.setYearNum(Integer.parseInt(yearNum));
        fundIncome.setMonthNum(Integer.parseInt(montehNum));
        fundIncome.setDayNum(Integer.parseInt(dayNum));
		fundIncomeService.updateById(fundIncome);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("fund:fundincome:delete")
    public R delete(@RequestBody Integer[] ids){
		fundIncomeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
