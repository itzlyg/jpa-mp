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

    private static final ThreadLocal<String> CONTEXT_HOLDER = new TransmittableThreadLocal<>();

    /**
     * 多租户与数据库的映射关系
     */
    public static Map<String, String> TENANT_DB = new HashMap<>();

    /**
     * 设置数据源
     *
     * @param tenantId tenantId
     */
    public static void setDbType(String tenantId) {
        CONTEXT_HOLDER.set(TENANT_DB.get(tenantId));
    }

    /**
     * 取得当前数据源
     *
     * @return 类型
     */
    public static String getDbType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        CONTEXT_HOLDER.remove();
    }
}
