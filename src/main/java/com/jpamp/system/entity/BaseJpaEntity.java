package com.jpamp.system.entity;

import com.jpamp.context.Context;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xyb
 * @Description
 * @Date 2022/3/20 16:55
 */
@MappedSuperclass
public abstract class BaseJpaEntity implements Serializable {
    @Id
    @GeneratedValue(generator = Context.JPA_ID_NAME)
    @GenericGenerator(name = Context.JPA_ID_NAME, strategy = Context.JPA_STRATEGY)
    private String id;
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
