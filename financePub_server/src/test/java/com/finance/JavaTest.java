package com.finance;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

/**
 * author jinsq
 *
 * @date 2019/5/30 9:15
 */
@Slf4j
public class JavaTest {

    public static void main(String[] args) throws Exception {

        Map<String, BigDecimal> map = new TreeMap<>
                (new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1) > Integer.parseInt(o2) ? 1: -1;
            }
        });
        map.put("11",new BigDecimal(400));
        map.put("1",new BigDecimal(100));
        map.put("2",new BigDecimal(200));
        map.put("3",new BigDecimal(300));
        map.put("10",new BigDecimal(500));
        map.put("12",new BigDecimal(600));
        map.put("13",new BigDecimal(700));
        map.put("11",new BigDecimal(222));
        for(String key : map.keySet()){
            System.out.println(key+","+map.get(key));
        }
    }
}
