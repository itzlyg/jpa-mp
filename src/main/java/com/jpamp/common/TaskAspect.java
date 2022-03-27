package com.jpamp.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class TaskAspect {

    private static final Logger LOG = LoggerFactory.getLogger(TaskAspect.class);

    @Around("@annotation(com.jpamp.annotation.TenantTask)")
    public Object point(ProceedingJoinPoint pjp) throws  Throwable {
        Object o = null;
        Map<String, String> tenantMap = DbContextHolder.TENANT_DB;
        try {
            for (Map.Entry<String, String> e : tenantMap.entrySet()) {
                LOG.info("切换到数据库{}执行定时任务。。。。", e.getValue());
                DbContextHolder.setDbType(e.getKey());
                o = pjp.proceed();
            }
        } finally {
            DbContextHolder.clearDbType();
        }
        return o;
    }
}
