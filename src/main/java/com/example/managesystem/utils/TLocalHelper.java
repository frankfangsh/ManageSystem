package com.example.managesystem.utils;


import com.example.managesystem.entity.BaseUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;


public class TLocalHelper {

    /**
     * 当前登录用户
     */
    public static final String USERINFO = "baseUser";


    /**
     * 用来保存当前线程中存放的数据(初始值，不是null)
     */
    private static ThreadLocal<Map<String, Object>> LOCAL_MAP =
            ThreadLocal.withInitial((Supplier<HashMap<String, Object>>) HashMap::new);


    /**
     * 设置当前登录用户
     *
     * @param userinfo
     */
    public static void setUserInfo(Object userinfo) {
        set(USERINFO, userinfo);
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static BaseUser getUserInfo() {
        return (BaseUser) get(USERINFO);
    }

    /**
     * 设置key-value
     *
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        getMap().put(key, value);
    }

    /**
     * 根据key获取value
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return getMap().get(key);
    }


    /**
     * 获取当前线程中存放的map
     *
     * @return
     */
    private static Map<String, Object> getMap() {
        Map<String, Object> result = LOCAL_MAP.get();
        if (Objects.isNull(result)) {
            result = new HashMap<>();
            LOCAL_MAP.set(result);
        }
        return result;
    }
}
