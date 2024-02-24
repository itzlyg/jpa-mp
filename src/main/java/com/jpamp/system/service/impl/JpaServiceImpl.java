package com.jpamp.system.service.impl;

import com.jpamp.system.entity.JpaInf;
import com.jpamp.system.repository.JpaInfRepository;
import com.jpamp.system.service.JpaService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Copyright Copyright (c) 2024
 * @author xieyubin
 * @since 2024-02-24 18:15:21
 */
@Service
public class JpaServiceImpl implements JpaService {

    @Resource
    private JpaInfRepository jpaInfRepository;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public JpaInf addOne(JpaInf user) {
        return jpaInfRepository.save(user);
    }
}
