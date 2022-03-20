package com.jpamp.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wt_jpa_inf")
public class JpaInf extends BaseJpaEntity {

    /** 昵称 */
    @Column(name = "nick_name")
    private String nickName;

    /** 用户ID */
    @Column(name = "user_id")
    private String userId;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
