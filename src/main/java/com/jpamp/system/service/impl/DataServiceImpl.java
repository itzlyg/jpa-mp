package com.jpamp.system.service.impl;

import com.jpamp.system.entity.JpaInf;
import com.jpamp.system.service.DataService;
import com.jpamp.system.service.JpaService;
import com.jpamp.system.service.UserService;
import com.jpamp.util.CustUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private JpaService jpaService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void testTran (String params){
        JpaInf jpa = new JpaInf();
        jpa.setCreateTime(LocalDateTime.now());
        jpa.setNickName(CustUtil.randomString(8));
        // 写入 jpa
        jpaService.addOne(jpa);
        // 写入 user
        userService.insert(params);
    }
}
