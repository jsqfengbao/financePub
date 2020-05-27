package com.finance.modules.my.consume.controller;

import java.math.BigDecimal;
import java.util.*;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import com.finance.common.utils.DateUtil;
import com.finance.common.utils.DateUtils;
import com.finance.common.utils.PageUtils;
import com.finance.common.utils.R;
import com.finance.modules.sys.controller.AbstractController;
import com.finance.modules.my.consume.entity.MyConsumeEntity;
import com.finance.modules.my.consume.service.MyConsumeService;
import com.finance.modules.my.consume.vo.PerDayConsumeVo;
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
 * @date 2019-05-08 20:17:21
 */
@RestController
@RequestMapping("my/myconsume")
public class MyConsumeController extends AbstractController {
    @Autowired
    private MyConsumeService myConsumeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("my:myconsume:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = myConsumeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("my:myconsume:info")
    public R info(@PathVariable("id") Integer id){
		MyConsumeEntity myConsume = myConsumeService.getById(id);

        return R.ok().put("myConsume", myConsume);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("my:myconsume:save")
    public R save(@RequestBody MyConsumeEntity myConsume){
        myConsume.setSysUserId(Integer.parseInt(getUserId()+""));
        myConsume.setSysUserName(getUser().getUsername());
		myConsumeService.save(myConsume);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("my:myconsume:update")
    public R update(@RequestBody MyConsumeEntity myConsume){
		myConsumeService.updateById(myConsume);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("my:myconsume:delete")
    public R delete(@RequestBody Integer[] ids){
		myConsumeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 统计本月的消费额度
     * @return
     */
    @RequestMapping("/statisThisMonthConsume")
    public R statisThisMonthConsume(){
        List<MyConsumeEntity> consumeEntityList = myConsumeService.queryConsumeByCondition();
        String nowDay = DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        String thisMonthStr = nowDay.substring(0,7);
        BigDecimal thisMonthConsume = new BigDecimal(0.0);
        for(MyConsumeEntity myConsumeEntity : consumeEntityList){
            String consumeTime = DateUtil.date2Str(myConsumeEntity.getConsumeTime(),"yyyy-MM-dd HH:mm:ss");
            if(consumeTime.startsWith(thisMonthStr)){
                thisMonthConsume = thisMonthConsume.add(myConsumeEntity.getConsumeMoney());
            }
        }
        return R.ok().put("thisMonthConsume",thisMonthConsume);
    }

    /**
     * 统计最近一个月的申请量和点击量
     * @return
     */
    @RequestMapping("/statisPerDayConsume")
    @RequiresPermissions("my:myconsume:statisPerDayUser")
    public R statisPerDayUser(){
        PerDayConsumeVo statisPerDayConsume = new PerDayConsumeVo();
        List<String> perDay = new ArrayList<>();   //日期
        List<Integer> consumeNumList = new ArrayList<>();  //每天的消费次数
        List<BigDecimal> consumeMoneyList = new ArrayList<>(); //每天的消费金额

        String nowDay = DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = new DateTime(nowDay, DatePattern.NORM_DATETIME_FORMAT);
        int month = Integer.parseInt(nowDay.substring(6,7));
        String year = dateTime.year()+"";
        String monthStr = "";
        if(month < 10){
            monthStr = "0"+month;
        }else{
            monthStr = month+"";
        }
        List<MyConsumeEntity> consumeEntityList = myConsumeService.queryConsumeByCondition();
        for(int i = 1;i<=31;i++){
            String thisDay = "";
            String nextDay = "";
            if(i < 10){
                thisDay = year+"-"+monthStr+"-0"+i;
                nextDay = year+"-"+monthStr+"-0"+(i+1);
            }else{
                thisDay = year+"-"+monthStr+"-"+i;
                nextDay = year+"-"+monthStr+"-"+i;
            }
            perDay.add(thisDay.substring(8,10));
            int consumeNum = 0;
            BigDecimal consumeMoneySum = new BigDecimal(0);
            for(MyConsumeEntity consumeEntity : consumeEntityList){
                String consumeTime = DateUtil.date2Str(consumeEntity.getConsumeTime(),"yyyy-MM-dd HH:mm:ss");
//                if(nextDay.equals(consumeTime.substring(0,10)))
//                    break;
                if(thisDay.equals(consumeTime.substring(0,10))){
                    consumeNum++;
                    consumeMoneySum = consumeMoneySum.add(consumeEntity.getConsumeMoney());
                }
            }
            consumeNumList.add(consumeNum);
            consumeMoneyList.add(consumeMoneySum);
        }
        statisPerDayConsume.setConsumeMoneyList(consumeMoneyList);
        statisPerDayConsume.setConsumeNumList(consumeNumList);
        statisPerDayConsume.setDayList(perDay);

        return R.ok().put("statisPerDayConsume",statisPerDayConsume);
    }
}
