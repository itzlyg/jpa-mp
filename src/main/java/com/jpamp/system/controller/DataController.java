package com.jpamp.system.controller;


import com.jpamp.system.entity.UserInf;
import com.jpamp.system.service.DataService;
import com.jpamp.system.vo.BaseRequest;
import com.jpamp.system.vo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/async/{type}")
    public String transmittable(@PathVariable("type") String type) {
        return dataService.async(type);
    }

    @PostMapping("/user_page")
    public BaseResponse<List<UserInf>> userPage(@RequestBody @Validated BaseRequest<String> request) {
        return dataService.userPage(request);
    }
}

