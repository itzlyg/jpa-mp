package com.jpamp.task;

import com.jpamp.annotation.TenantTask;
import com.jpamp.system.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QueryTask {

    @Autowired
    private DataService dataService;

    private Logger log = LoggerFactory.getLogger(QueryTask.class);

    @TenantTask
    @Scheduled(cron = "0 0/1 * * * ?")
    public void queryData() {
        long l = dataService.queryCount();
        log.info("查询到的用户数量为{}", l);
    }
}
