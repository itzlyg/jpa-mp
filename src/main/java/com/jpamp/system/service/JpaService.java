package com.jpamp.system.service;

import com.jpamp.system.entity.JpaInf;

public interface JpaService {

    JpaInf addOne(JpaInf jpa);

    JpaInf findById(Long id) ;
}
