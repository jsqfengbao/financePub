package com.finance.modules.fund.utils;

import java.util.List;

/**
 * author jinsq
 *
 * @date 2019/7/17 9:37
 */
public class ListUtil {

    /**
     * 将数组转换成元祖
     * @param numberList
     * @return
     */
    public static String turnListToYZ(List<Long> numberList){
        StringBuilder sb = new StringBuilder();
//        sb.append("(");
        for(int i = 0;i<numberList.size();i++) {
            if(i == numberList.size() -1){
                sb.append(numberList.get(i));
            }else{
                sb.append(numberList.get(i) + ",");
            }
        }
//        sb.append(")");
        return sb.toString();
    }
}
