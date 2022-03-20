package com.jpamp.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jpamp.system.entity.UserInf;
import com.jpamp.system.mapper.UserInfMapper;
import com.jpamp.system.service.UserService;
import com.jpamp.system.vo.BaseRequest;
import com.jpamp.system.vo.BaseResponse;
import com.jpamp.util.CustUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class UserServiceImpl extends ServiceImpl<UserInfMapper, UserInf> implements UserService {

    @Autowired
    private UserInfMapper mapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void insert (String params){
        UserInf inf = new UserInf();
        inf.setAge(12);
        inf.setCreateTime(LocalDateTime.now());
        inf.setName(CustUtil.randomString(6));
        save(inf);
        if ("exception".equals(params)) {
            throw new RuntimeException("测试异常");
        }
    }

    @Override
    public BaseResponse<List<UserInf>> userPage (BaseRequest<String> request){
        UserInf params = new UserInf();
        params.setAge(12);
        return CustUtil.page(request.getPage(), pa -> mapper.userPage(pa, params)) ;
    }
}
