package com.jpamp.system.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "wt_jpa_inf")
public class JpaInf implements Serializable {

    @Id
    @GeneratedValue(generator = "jpaIdGenerator")
    @GenericGenerator(name = "jpaIdGenerator", strategy = "com.jpamp.util.JpaIdGenerator")
    private String id;

    /** 昵称 */
    @Column(name = "nick_name")
    private String nickName;

    /** 用户ID */
    @Column(name = "user_id")
    private String userId;

    /** 创建时间 */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 修改时间 */
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}
