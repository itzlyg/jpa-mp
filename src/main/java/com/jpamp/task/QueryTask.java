package com.jpamp.task;

import com.jpamp.annotation.TenantTask;
import com.jpamp.system.service.DataService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Copyright Copyright (c) 2024
 * @author xieyubin
 * @since 2024-02-24 18:19:16
 */
@Component
public class QueryTask {

    @Resource
    private DataService dataService;

    private static final Logger log = LoggerFactory.getLogger(QueryTask.class);

    @TenantTask
    @Scheduled(cron = "0 0/1 * * * ?")
    public void queryData() {
        long l = dataService.queryCount();
        log.info("查询到的用户数量为{}", l);
    }
}
