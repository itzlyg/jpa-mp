package com.jpamp.system.service.impl;

import com.jpamp.system.entity.JpaInf;
import com.jpamp.system.service.DataService;
import com.jpamp.system.service.JpaService;
import com.jpamp.system.service.OrderService;
import com.jpamp.system.service.UserService;
import com.jpamp.util.CustUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DataServiceImpl implements DataService {

    private Logger log = LoggerFactory.getLogger(DataServiceImpl.class);

    @Autowired
    private JpaService jpaService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Override
    public String addJpa() {
        JpaInf inf = new JpaInf();
        inf.setCreateTime(LocalDateTime.now());
        jpaService.addOne(inf);
        return CustUtil.result();
    }

    @Override
    public String addUser(String params) {
        userService.insert(params);
        return CustUtil.result();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String testTran (String params){
        JpaInf jpa = new JpaInf();
        jpa.setCreateTime(LocalDateTime.now());
        jpa.setNickName(CustUtil.randomString(8));
        // 写入 jpa
        jpaService.addOne(jpa);
        // 写入 user
        userService.insert(params);
        orderService.asyncTest();
        return CustUtil.result();
    }

    @Override
    public String transmittable(){
//        ExecutorService service = TtlExecutors.getTtlExecutorService(Executors.newCachedThreadPool());
        ExecutorService service = Executors.newSingleThreadExecutor();
//        ThreadLocal<String> context = new ThreadLocal<>();
        ThreadLocal<String> context = new InheritableThreadLocal<>();
//        ThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("transmittable ThreadLocal");
        service.execute(() -> {
            try {
                Thread.sleep(10 * 1000);
                log.info("task {}", context.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return CustUtil.result();
    }
}
