package com.jpamp.system.service.impl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.jpamp.common.DbContextHolder;
import com.jpamp.system.entity.JpaInf;
import com.jpamp.system.entity.UserInf;
import com.jpamp.system.service.DataService;
import com.jpamp.system.service.JpaService;
import com.jpamp.system.service.OrderService;
import com.jpamp.system.service.UserService;
import com.jpamp.system.vo.BaseRequest;
import com.jpamp.system.vo.BaseResponse;
import com.jpamp.util.CustUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private JpaService jpaService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private Logger log = LoggerFactory.getLogger(DataServiceImpl.class);

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
    public String testTran(String params) {
        JpaInf jpa = new JpaInf();
        jpa.setCreateTime(LocalDateTime.now());
        jpa.setNickName(CustUtil.randomString(8));
        // ?????? jpa
        jpaService.addOne(jpa);
        // ?????? user
        userService.insert(params);
        orderService.asyncTest();
        return CustUtil.result();
    }

    @Override
    public long queryCount() {
        return userService.count();
    }

    @Override
    public String async(String type) {
        ExecutorService executor = new ScheduledThreadPoolExecutor(7,
                new BasicThreadFactory.Builder()
                        .namingPattern("system-pool-%d")
                        .daemon(true)
                        .build());
        if ("transmittable".equals(type)) {
            executor = TtlExecutors.getTtlExecutorService(executor);
        }
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            ThreadLocal<String> context;
            // util ????????? ??????????????????????????????????????????????????????
            if ("inheritable".equals(type)) {
                context = new InheritableThreadLocal<>();
                // ??????????????????
            } else if ("transmittable".equals(type)) {
                context = new TransmittableThreadLocal<>();
            } else {
                context = new ThreadLocal<>();
            }
            futures.add(asyncThread(executor, context));
        }
        int s = 0;
        CompletableFuture<Void> downFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        CompletableFuture<List<Integer>> ints = downFutures.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        try {
            List<Integer> list = ints.get();
            s += list.stream().collect(Collectors.summingInt(Integer::intValue));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        return "?????????????????????" + s;
    }

    @Override
    public BaseResponse<List<UserInf>> userPage(BaseRequest<String> request) {
        return userService.userPage(request);
    }

    @Async
    @Override
    public void async() {
        try {
            log.info("??????  async,???????????????:{}", DbContextHolder.getDbType());

            Thread.sleep(15 * 1000);
            log.info("  async ??????");
            log.info("async ??????,???????????????:{}", DbContextHolder.getDbType());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private CompletableFuture<Integer> asyncThread(ExecutorService service, ThreadLocal<String> context) {
        String id = System.currentTimeMillis() + CustUtil.randomString(8);
        context.set(id);
        return CompletableFuture.supplyAsync(() -> {
            try {
                String threadValue = context.get();
                if (!id.equals(threadValue)) {
                    log.error("????????????????????????????????????--> {} --- {}", id, threadValue);
                    return 1;
                }
            } finally {
                context.remove();
            }
            return 0;
        }, service);
    }
}
