package com.jpamp.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jpamp.system.entity.OrderInf;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

/**
*  Mapper 接口
* @Description
* @Copyright Copyright (c) 2022
* @author xieyubin
* @since 2022-03-16 09:39:53
*/
public interface OrderInfMapper extends BaseMapper<OrderInf> {

    @Select("SELECT price from t_order where user_id =#{userId}")
    BigDecimal getPriceByUserId(Integer userId);
}
