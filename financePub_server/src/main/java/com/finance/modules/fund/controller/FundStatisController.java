package com.finance.modules.fund.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.finance.common.utils.DateUtil;
import com.finance.common.utils.R;
import com.finance.modules.fund.vo.FundHomeStatisVo;
import com.finance.modules.fund.entity.FundBudgetEntity;
import com.finance.modules.fund.entity.FundIncomeEntity;
import com.finance.modules.fund.entity.FundPayEntity;
import com.finance.modules.fund.entity.FundYearBudgetEntity;
import com.finance.modules.fund.service.FundBudgetService;
import com.finance.modules.fund.service.FundIncomeService;
import com.finance.modules.fund.service.FundPayService;
import com.finance.modules.fund.service.FundYearBudgetService;
import com.finance.modules.sys.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * author jinsq
 *
 * @date 2019/7/12 15:51
 */
@RestController
@RequestMapping("fund/statis")
public class FundStatisController extends AbstractController {

    @Autowired
    private FundPayService fundPayService;
    @Autowired
    private FundBudgetService fundBudgetService;
    @Autowired
    private FundIncomeService fundIncomeService;
    @Autowired
    private FundYearBudgetService fundYearBudgetService;

    @RequestMapping(value = "/fundHomeStatis")
    public R queryHomeStatis(){
        return R.ok().put("homeData",getHomeStatis(getUserId()));
    }

    private FundHomeStatisVo getHomeStatis(long sysUserId){
        FundHomeStatisVo home = new FundHomeStatisVo();
        Map<String,Object> mapAllPayTypeData = getThisPay(getUserId());
        home.setThisMonthDayName((List<String>) mapAllPayTypeData.get("thisMonthDayName"));
        home.setThisYearMonthName((List<String>) mapAllPayTypeData.get("thisYearMonthName"));
        home.setThisMonthPayDayName((List<String>) mapAllPayTypeData.get("thisMonthPayDayName"));
        home.setThisMonthPayDayData((JSONArray) mapAllPayTypeData.get("thisMonthPayDayData"));
        home.setThisMonthPayTypeName((List<String>) mapAllPayTypeData.get("thisMonthPayTypeName"));
        home.setThisMonthPayTypeData((JSONArray) mapAllPayTypeData.get("thisMonthPayTypeData"));
        home.setThisYearPayMonthName((List<String>) mapAllPayTypeData.get("thisYearPayMonthName"));
        BigDecimal thisYearTotalPayMoney = (BigDecimal) mapAllPayTypeData.get("thisYearTotalPayMoney");
        JSONArray thisYearPayMonthData = (JSONArray) mapAllPayTypeData.get("thisYearPayMonthData");
        home.setThisYearPayMonthData(thisYearPayMonthData);
        home.setThisYearTotalPayMoney(thisYearTotalPayMoney);
        Map<String,Object> mapAllIncomeTypeData =getIncome(getUserId());
        home.setThisMonthIncomeDayName((List<String>) mapAllIncomeTypeData.get("thisMonthIncomeDayName"));
        home.setThisMonthIncomeDayData((JSONArray) mapAllIncomeTypeData.get("thisMonthIncomeDayData"));
        home.setThisMonthIncomeTypeName((List<String>) mapAllIncomeTypeData.get("thisMonthIncomeTypeName"));
        home.setThisMonthIncomeTypeData((JSONArray) mapAllIncomeTypeData.get("thisMonthIncomeTypeData"));
        home.setThisYearIncomeMonthName((List<String>) mapAllIncomeTypeData.get("thisYearIncomeMonthName"));
        JSONArray thisYearIncomeMonthData = (JSONArray) mapAllIncomeTypeData.get("thisYearIncomeMonthData");
        home.setThisYearIncomeMonthData(thisYearIncomeMonthData);
        BigDecimal thisYearTotalIncomeMoney = (BigDecimal) mapAllIncomeTypeData.get("thisYearTotalIncomeMoney");
        home.setThisYearTotalIncomeMoney(thisYearTotalIncomeMoney);
        Map<String,Object> mapAllBudgetData = getBudget(getUserId());
        home.setThisMonthPayBudgetTypeName((List<String>) mapAllBudgetData.get("thisMonthPayBudgetTypeName"));
        home.setThisMonthPayBudgetTypeData((JSONArray) mapAllBudgetData.get("thisMonthPayBudgetTypeData"));
        Map<String,Object> mapAllYearBudgetData = getYearBudget(getUserId());
        BigDecimal thisYearTotalBudgetIncomeMoney = (BigDecimal) mapAllYearBudgetData.get("thisYearTotalBudgetIncomeMoney");
        BigDecimal thisYearTotalBudgetPayMoney = (BigDecimal) mapAllYearBudgetData.get("thisYearTotalBudgetPayMoney");
        home.setThisYearTotalBudgetIncomeMoney(thisYearTotalBudgetIncomeMoney);
        home.setThisYearTotalBudgetPayMoney(thisYearTotalBudgetPayMoney);
        home.setThisYearTotalIncomeBudgetRatio(getRatio(thisYearTotalIncomeMoney,thisYearTotalBudgetIncomeMoney));
        home.setThisYearTotalPayBudgetRatio(getRatio(thisYearTotalPayMoney,thisYearTotalBudgetPayMoney));
        home.setThisYearPayIncomeRatio(getThisYearPayIncomeRatio(thisYearPayMonthData,thisYearIncomeMonthData));
        Map<String,Object> payIncomeHead5 = getThisMonthHead5(sysUserId);
        home.setThisMonthPayHead5Data((JSONArray) payIncomeHead5.get("thisMonthPayHead5Data"));
        home.setThisMonthIncomeHead5Data((JSONArray) payIncomeHead5.get("thisMonthIncomeHead5Data"));
        return home;
    }

    /**
     * 获取支出数据
     * @return
     */
    public Map<String,Object> getThisPay(long sysUserId){
        Map<String,Object> map = new HashMap<>();
        int year = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(0,4));
        int month = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(5,7));
        int day = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(8,10));

        List<String> thisMonthDayName = new ArrayList<>();  //当月的每日日期名
        List<String> thisYearMonthName = new ArrayList<>(); //当年的每月日期名
        for(int i = 1;i<=day;i++){
             thisMonthDayName.add(i+"");
        }
        for(int i = 1;i<=month;i++){
            thisYearMonthName.add(i+"");
        }
        Map<String,BigDecimal> tempDayMap = new TreeMap<>(new Comparator<String>() {  //当月的每日支出数据临时Map
            @Override
            public int compare(String o1, String o2) {
                if(Integer.parseInt(o1) > Integer.parseInt(o2)){
                    return 1;
                }else if(Integer.parseInt(o1) < Integer.parseInt(o2)){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        Map<String,BigDecimal> tempTypeMap = new TreeMap<>(); // 当月支出类别Map
        Map<String,BigDecimal> tempMonthMap = new TreeMap<>(new Comparator<String>() { //当年支出月份数据临时Map
            @Override
            public int compare(String o1, String o2) {
                if(Integer.parseInt(o1) > Integer.parseInt(o2)){
                    return 1;
                }else if(Integer.parseInt(o1) < Integer.parseInt(o2)){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        BigDecimal thisYearTotalPayMoney = new BigDecimal(0.00);  //今年总支出数额
        List<FundPayEntity> fundPayEntities = fundPayService.getThisYearPay(sysUserId,year);
        for(FundPayEntity fundPayEntity : fundPayEntities){
            if(fundPayEntity.getMonthNum().equals(month)){
                tempDayMap = putMap(tempDayMap,fundPayEntity.getDayNum()+"",fundPayEntity.getMoney());
                tempTypeMap = putMap(tempTypeMap,fundPayEntity.getFundTypeName(),fundPayEntity.getMoney());
            }
            tempMonthMap = putMap(tempMonthMap,fundPayEntity.getMonthNum()+"",fundPayEntity.getMoney());
            thisYearTotalPayMoney = fundPayEntity.getMoney().add(thisYearTotalPayMoney);
        }
        for(int i = 1;i<=day;i++){
            if(tempDayMap.containsKey(String.valueOf(i))){
                continue;
            }else{
                tempDayMap.put(i+"",new BigDecimal(0.00));
            }
        }
        for(int i = 1;i<=month;i++){
            if(tempMonthMap.containsKey(String.valueOf(i))){
                continue;
            }else {
                tempMonthMap.put(i+"",new BigDecimal(0.00));
            }
        }

        map = putJSON(map,"thisMonthPayDayName","thisMonthPayDayData",tempDayMap);
        map = putJSON(map,"thisMonthPayTypeName","thisMonthPayTypeData",tempTypeMap);
        map = putJSON(map,"thisYearPayMonthName","thisYearPayMonthData",tempMonthMap);
        map.put("thisYearTotalPayMoney",thisYearTotalPayMoney);
        map.put("thisMonthDayName",thisMonthDayName);
        map.put("thisYearMonthName",thisYearMonthName);
        return map;
    }

    /**
     * 获取收入数据
     * @param sysUserId
     * @return
     */
    private Map<String,Object> getIncome(long sysUserId) {
        Map<String,Object> map = new HashMap<>();
        int year = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(0,4));
        int month = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(5,7));
        int day = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(8,10));

        Map<String,BigDecimal> tempDayMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(Integer.parseInt(o1) > Integer.parseInt(o2)){
                    return 1;
                }else if(Integer.parseInt(o1) < Integer.parseInt(o2)){
                    return -1;
                }else{
                    return 0;
                }
            }
        }); // 当月支出日期Map
        Map<String,BigDecimal> tempTypeMap = new TreeMap<>(); // 当月支出类别Map
        Map<String,BigDecimal> tempMonthMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(Integer.parseInt(o1) > Integer.parseInt(o2)){
                    return 1;
                }else if(Integer.parseInt(o1) < Integer.parseInt(o2)){
                    return -1;
                }else{
                    return 0;
                }
            }
        }); //当年支出月份Map
        BigDecimal thisYearTotalIncomeMoney = new BigDecimal(0.00);
        List<FundIncomeEntity> fundIncomeEntities = fundIncomeService.getThisYearIncome(sysUserId,year);
        for(FundIncomeEntity fundIncomeEntity : fundIncomeEntities){
            if(fundIncomeEntity.getMonthNum().equals(month)){
                tempDayMap = putMap(tempDayMap,fundIncomeEntity.getDayNum()+"",fundIncomeEntity.getMoney());
                tempTypeMap = putMap(tempTypeMap,fundIncomeEntity.getFundTypeName(),fundIncomeEntity.getMoney());
            }
            tempMonthMap = putMap(tempMonthMap,fundIncomeEntity.getMonthNum()+"",fundIncomeEntity.getMoney());
            thisYearTotalIncomeMoney = fundIncomeEntity.getMoney().add(thisYearTotalIncomeMoney);
        }
        for(int i = 1;i<=day;i++){
            if(tempDayMap.containsKey(String.valueOf(i))){
                continue;
            }else{
                tempDayMap.put(i+"",new BigDecimal(0.00));
            }
        }
        for(int i = 1;i<=month;i++){
            if(tempMonthMap.containsKey(String.valueOf(i))){
                continue;
            }else {
                tempMonthMap.put(i+"",new BigDecimal(0.00));
            }
        }
        map = putJSON(map,"thisMonthIncomeDayName","thisMonthIncomeDayData",tempDayMap);
        map = putJSON(map,"thisMonthIncomeTypeName","thisMonthIncomeTypeData",tempTypeMap);
        map = putJSON(map,"thisYearIncomeMonthName","thisYearIncomeMonthData",tempMonthMap);
        map.put("thisYearTotalIncomeMoney",thisYearTotalIncomeMoney);
        return map;
    }

    /**
     * 获取每月预算数据
     * @param sysUserId
     * @return
     */
    private Map<String,Object> getBudget(long sysUserId){
        Map<String,Object> map = new HashMap<>();
        int year = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(0,4));
        int month = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(5,7));
        List<FundBudgetEntity> fundBudgetEntities = fundBudgetService.list();
        Map<String,BigDecimal> tempBudgetMonthMap = new TreeMap<>(); // 当月支出日期Map
        for(FundBudgetEntity fundBudget : fundBudgetEntities){
            if(fundBudget.getYearNum().equals(year) && fundBudget.getSysUserId().equals(sysUserId)){
                if(fundBudget.getMonthNum().equals(month)){
                    tempBudgetMonthMap = putMap(tempBudgetMonthMap,fundBudget.getFundTypeName(),fundBudget.getMoney());
                }
            }
        }
        map = putJSON(map,"thisMonthPayBudgetTypeName","thisMonthPayBudgetTypeData",tempBudgetMonthMap);

        return map;
    }

    /**
     * 获取每年预算数据
     * @param sysUserId
     * @return
     */
    private Map<String,Object> getYearBudget(long sysUserId){
        Map<String,Object> map = new HashMap<>();
        int year = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(0,4));
        int month = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(5,7));
        List<FundYearBudgetEntity> fundYearBudgetEntities = fundYearBudgetService.list();
        Map<String,BigDecimal> tempBudgetMonthMap = new TreeMap<>(); // 当月支出日期Map
        for(FundYearBudgetEntity fundYearBudget : fundYearBudgetEntities){
            if(fundYearBudget.getYearNum().equals(year) && fundYearBudget.getSysUserId().equals(sysUserId)){
                map.put("thisYearTotalBudgetIncomeMoney",fundYearBudget.getFundYearIncome());
                map.put("thisYearTotalBudgetPayMoney",fundYearBudget.getFundYearPay());
                break;
            }
        }
        if(!map.containsKey("thisYearTotalBudgetIncomeMoney")){
            map.put("thisYearTotalBudgetIncomeMoney",new BigDecimal(0.00));
        }
        if(!map.containsKey("thisYearTotalBudgetPayMoney")){
            map.put("thisYearTotalBudgetPayMoney",new BigDecimal(0.00));
        }
        return map;
    }

    /**
     * 获取本月收入与支出的前五项数据
     * @param sysUserId
     * @return
     */
    private Map<String,Object> getThisMonthHead5(long sysUserId){
        Map<String,Object> map = new HashMap<>();
        int year = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(0,4));
        int month = Integer.parseInt(DateUtil.date2Str(new Date(),"yyyy-MM-dd").substring(5,7));
        List<FundIncomeEntity> fundIncomeHead5 = fundIncomeService.getThisMonthIncomeHead5(sysUserId,year,month);
        List<FundPayEntity> fundPayHead5 = fundPayService.getThisMonthPayHead5(sysUserId,year,month);
        JSONArray incomeArray = new JSONArray();
        for(FundIncomeEntity fundIncomeEntity : fundIncomeHead5){
            JSONObject income = new JSONObject();
            income.put("name",fundIncomeEntity.getContent());
            income.put("value",fundIncomeEntity.getMoney());

            incomeArray.add(income);
        }
        JSONArray payArray = new JSONArray();
        for(FundPayEntity fundPayEntity : fundPayHead5){
            JSONObject pay = new JSONObject();
            pay.put("name",fundPayEntity.getContent());
            pay.put("value",fundPayEntity.getMoney());

            payArray.add(pay);
        }
        map.put("thisMonthPayHead5Data",payArray);
        map.put("thisMonthIncomeHead5Data",incomeArray);
        return map;
    }
    private JSONArray getThisYearPayIncomeRatio(JSONArray payArray,JSONArray incomeArray){
        JSONArray result = new JSONArray();
        for(int i = 0;i<payArray.size();i++){
            JSONObject json = new JSONObject();
            JSONObject payObject = payArray.getJSONObject(i);
            JSONObject incomeObject = incomeArray.getJSONObject(i);
            String name = payObject.getString("name");
            BigDecimal payValue = payObject.getBigDecimal("value");
            BigDecimal incomeValue = incomeObject.getBigDecimal("value");
            json.put("name",name);
            BigDecimal num = new BigDecimal(0.00);
            if(payValue.compareTo(BigDecimal.ZERO) == 0 || incomeValue.compareTo(BigDecimal.ZERO) == 0){

            }else{
                num = payValue.divide(incomeValue,2, ROUND_HALF_UP);
            }
            json.put("value",num);

            result.add(json);
        }
        return result;
    }

    //判断并正装数据map
    private Map putMap(Map<String,BigDecimal> tempMap,String key,BigDecimal value){
        if(tempMap.containsKey(key)){
            BigDecimal d = value;
            BigDecimal total = d.add(tempMap.get(key));
            tempMap.replace(key,total);
        }else{
            tempMap.put(key,value);
        }
        return tempMap;
    }

    /**
     * 组装JSON数据
     * @param map 返回的Map
     * @param key1 name名
     * @param key2 value名
     * @param tempMap 中间临时Map
     * @return
     */
    private Map putJSON(Map<String,Object> map,String key1,String key2,Map<String,BigDecimal> tempMap){
        map.put(key1,Arrays.asList(tempMap.keySet().toArray()));
        JSONArray thiseArray = new JSONArray();
        for(String key : tempMap.keySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",key);
            jsonObject.put("value",tempMap.get(key));

            thiseArray.add(jsonObject);
        }
        map.put(key2,thiseArray);

        return map;
    }

    /**
     * 获取百分比
     * @param one
     * @param two
     * @return
     */
    private String getRatio(BigDecimal one,BigDecimal two){
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        if(two.compareTo(BigDecimal.ZERO) == 0){
            return "0.00%";
        }
        BigDecimal d = one.divide(two,2,ROUND_HALF_UP).multiply(new BigDecimal(100.00));
        String num = df.format(d.doubleValue())+'%';//返回的是String类型
        return num;
    }
}
