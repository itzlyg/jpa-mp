package com.jpamp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jpamp.system.entity.UserInf;
import com.jpamp.system.vo.BaseRequest;
import com.jpamp.system.vo.BaseResponse;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author YX
 * @since 2022/3/15 23:43
 */
public interface UserService extends IService<UserInf> {

    void insert (String params);

    BaseResponse<List<UserInf>> userPage (BaseRequest<String> request);
}
