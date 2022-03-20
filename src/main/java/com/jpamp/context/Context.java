package com.jpamp.context;

/**
 * @author xyb
 * @Description
 * @Date 2022/3/20 22:20
 */
public class Context {

    /**
     * 基类包
     */
    public static final String ENTITY_PACKAGES = "com.jpamp.*.entity";
    /**
     * repository 基类包
     */
    public static final String JPA_REPOSITORY = "com.jpamp.*.repository";
    /**
     * JPA id
     */
    public static final String JPA_STRATEGY = "com.jpamp.util.JpaIdGenerator";

    /**
     * JPA id
     */
    public static final String JPA_ID_NAME = "jpaIdGenerator";
    /** mapper */
    public static final String MP_MAPPER = "com.jpamp.*.mapper";
}
