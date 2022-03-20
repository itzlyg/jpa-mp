package com.jpamp.system.service.impl;

import com.jpamp.system.entity.JpaInf;
import com.jpamp.system.repository.JpaInfRepository;
import com.jpamp.system.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JpaServiceImpl implements JpaService {

    @Autowired
    private JpaInfRepository jpaInfRepository;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public JpaInf addOne(JpaInf user) {
        return jpaInfRepository.save(user);
    }
}
