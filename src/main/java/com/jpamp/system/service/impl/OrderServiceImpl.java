package com.jpamp.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jpamp.system.entity.OrderInf;
import com.jpamp.system.mapper.OrderInfMapper;
import com.jpamp.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


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
    @Autowired
    private OrderInfMapper orderMapper;

    @Override
    public List<OrderInf> getOrderList() {
        return this.list();
    }
    @Override
    public BigDecimal getOrderPriceByUserId(Integer userId) {
        return orderMapper.getPriceByUserId(userId);
    }
}
