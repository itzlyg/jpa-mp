package com.jpamp.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jpamp.common.DbContextHolder;
import com.jpamp.system.entity.OrderInf;
import com.jpamp.system.mapper.OrderInfMapper;
import com.jpamp.system.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * <p>
 *
 * </p>
 *
 * @author YX
 * @since 2022/3/15 23:43
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderInfMapper, OrderInf> implements OrderService {

    private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Async
    @Override
    public void asyncTest() {
        try {
            log.info("进入到异步方法～～～～");
            Thread.sleep(10 * 1000);
            log.info("测试异步获取到db，{}", DbContextHolder.getDbType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
