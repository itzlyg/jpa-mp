package com.jpamp.common;

import org.apache.commons.lang3.RandomUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * aop
 *
 * @author YX
 * @Description
 * @Date 2022/3/15 22:43
 */
//@Component
//@Aspect
//@Order(-100)
public class DsSwitchAspect {

    private static final Logger log = LoggerFactory.getLogger(DsSwitchAspect.class);

    @Pointcut("execution(* com.jpamp.system.controller..*.*(..))")
    private void controllerAsp() {
    }

    @Around("controllerAsp()")
    public Object controllerAop(ProceedingJoinPoint joinPoint) throws Throwable {
        // mock user
        Integer u = RandomUtils.nextInt(0, 10);
        String tenantId = String.valueOf(u % 2);
        log.info("切换到数据库{}，执行业务逻辑...", DbContextHolder.TENANT_DB.get(tenantId));
        DbContextHolder.setDbType(tenantId);
        try {
            return joinPoint.proceed();
        } finally {
            log.info("清除 datasource router...");
//            DbContextHolder.clearDbType();
        }
    }
}
