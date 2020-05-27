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

import com.finance.modules.fund.entity.FundPayEntity;
import com.finance.modules.fund.service.FundPayService;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-11 09:41:42
 */
@RestController
@RequestMapping("fund/fundpay")
public class FundPayController extends AbstractController {
    @Autowired
    private FundPayService fundPayService;
    @Autowired
    private FundTypeService fundTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("fund:fundpay:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("sysUserId",getUserId());
        PageUtils page = fundPayService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("fund:fundpay:info")
    public R info(@PathVariable("id") Integer id){
		FundPayEntity fundPay = fundPayService.getById(id);

        return R.ok().put("fundPay", fundPay);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("fund:fundpay:save")
    public R save(@RequestBody FundPayEntity fundPay){
        fundPay.setSysUserId(getUserId());
        fundPay.setSysUserName(getUser().getUsername());
        FundTypeEntity fundTypeEntity = fundTypeService.getById(fundPay.getFundTypeId());
        fundPay.setFundTypeName(fundTypeEntity.getName());
        String montehNum = DateUtil.date2Str(fundPay.getPayTime(),"yyyy-MM-dd").substring(5,7);
        String yearNum = DateUtil.date2Str(fundPay.getPayTime(),"yyyy-MM-dd").substring(0,4);
        String dayNum = DateUtil.date2Str(fundPay.getPayTime(),"yyyy-MM-dd").substring(8,10);
        fundPay.setDayNum(Integer.parseInt(dayNum));
        fundPay.setMonthNum(Integer.parseInt(montehNum));
        fundPay.setYearNum(Integer.parseInt(yearNum));
		fundPayService.save(fundPay);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("fund:fundpay:update")
    public R update(@RequestBody FundPayEntity fundPay){
        String montehNum = DateUtil.date2Str(fundPay.getPayTime(),"yyyy-MM-dd").substring(5,7);
        String yearNum = DateUtil.date2Str(fundPay.getPayTime(),"yyyy-MM-dd").substring(0,4);
        String dayNum = DateUtil.date2Str(fundPay.getPayTime(),"yyyy-MM-dd").substring(8,10);
        fundPay.setDayNum(Integer.parseInt(dayNum));
        fundPay.setMonthNum(Integer.parseInt(montehNum));
        fundPay.setYearNum(Integer.parseInt(yearNum));
		fundPayService.updateById(fundPay);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("fund:fundpay:delete")
    public R delete(@RequestBody Integer[] ids){
		fundPayService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
