package com.jpamp.system.controller;


import com.jpamp.system.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private DataService dataService;

    @GetMapping("/jpa")
    public String jpa() {
        return dataService.addJpa();
    }

    @GetMapping("/tra/{params}")
    public String testTran(@PathVariable("params") String params) {
        return dataService.testTran(params);
    }

    @GetMapping("/user/{params}")
    public String userInsert(@PathVariable("params") String params) {
        return dataService.addUser(params);
    }

    @GetMapping("/transmittable")
    public String transmittable() {
        return dataService.transmittable();
    }
}

