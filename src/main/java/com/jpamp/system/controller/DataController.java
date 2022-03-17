package com.jpamp.system.controller;


import com.jpamp.system.entity.JpaInf;
import com.jpamp.system.entity.UserInf;
import com.jpamp.system.service.DataService;
import com.jpamp.system.service.JpaService;
import com.jpamp.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 请求
 * @Description
 * @author YX
 * @Date 2022/3/15 23:43
 */
@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    private UserService userService;
    @Autowired
    private DataService dataService;

    @Autowired
    private JpaService jpaService;

    @GetMapping("/users")
    public List<UserInf> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/jpa")
    public String jpa() {
        JpaInf inf = new JpaInf();
        inf.setCreateTime(LocalDateTime.now());
        jpaService.addOne(inf);
        return System.currentTimeMillis() + "";
    }


    @GetMapping("/tra/{params}")
    public String testTran(@PathVariable("params") String params) {
        dataService.testTran(params);
        return System.currentTimeMillis() + "";
    }

    @GetMapping("/user/{params}")
    public String userInsert(@PathVariable("params") String params) {
        userService.insert(params);
        return System.currentTimeMillis() + "";
    }

    @GetMapping("/transmittable")
    public String transmittable() {
        dataService.transmittable();
        return System.currentTimeMillis() + "";
    }
}

