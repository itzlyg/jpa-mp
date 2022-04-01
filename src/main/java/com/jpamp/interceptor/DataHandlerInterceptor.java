package com.jpamp.interceptor;

import com.jpamp.common.DbContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 拦截器设置数据源信息
 */
@Component
public class DataHandlerInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(DataHandlerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer u = RandomUtils.nextInt(0, 10);
        String tenantId = String.valueOf(u % 2);
        log.info("切换到数据库{}，执行业务逻辑...", DbContextHolder.TENANT_DB.get(tenantId));
        DbContextHolder.setDbType(tenantId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle ~~~~~~");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("before clearDbType {}", DbContextHolder.getDbType());
        DbContextHolder.clearDbType();
        log.info("after clearDbType {}", DbContextHolder.getDbType());
        log.info("afterCompletion ****************");
    }
}
