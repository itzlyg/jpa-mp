package com.jpamp.system.service;

import com.jpamp.system.entity.UserInf;
import com.jpamp.system.vo.BaseRequest;
import com.jpamp.system.vo.BaseResponse;

import java.util.List;

public interface DataService {

    String addJpa ();

    String addUser (String params);

    String testTran (String params);

    String async(String type);

    long queryCount ();

    BaseResponse<List<UserInf>> userPage (BaseRequest<String> request);;
}
