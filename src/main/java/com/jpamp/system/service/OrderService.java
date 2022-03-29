package com.jpamp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jpamp.system.entity.OrderInf;


/**
 * <p>
 *
 * </p>
 *
 * @author YX
 * @since 2022/3/15 23:43
 */
public interface OrderService extends IService<OrderInf> {

    void asyncTest();
}
