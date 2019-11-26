package com.fun.business.sharon.biz.business.service.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @ClassName: IData1
 * @Date: 2019/10/17 15:25
 * @Version: 0.0.1
 */
public class IData4 {

    static Map<String, Integer> userMap = new HashMap<>(2000);

    static {
        userMap.putAll(IData1.userMap);
        userMap.putAll(IData2.userMap);
        userMap.putAll(IData3.userMap);
    }
}
