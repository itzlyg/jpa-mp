package com.jpamp.common;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * DbContextHolder
 *
 * @author YX
 * @Description
 * @Date 2022/3/15 22:43
 */
public class DbContextHolder {

    private static final ThreadLocal<String> contextHolder = new TransmittableThreadLocal<>();

    /**
     * 多租户与数据库的映射关系
     */
    public static Map<String, String> TENANT_DB = new HashMap();

    /**
     * 设置数据源
     *
     * @param tenantId
     */
    public static void setDbType(String tenantId) {
        contextHolder.set(TENANT_DB.get(tenantId));
    }

    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDbType() {
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}
