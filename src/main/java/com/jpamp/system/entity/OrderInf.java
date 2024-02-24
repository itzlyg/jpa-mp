package com.jpamp.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* 表
* @Description
* @Copyright Copyright (c) 2022
* @author xieyubin
* @since 2022-03-16 09:39:53
*/
@TableName("wt_order_inf")
public class OrderInf implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /** 订单号 */
    @TableField("order_no")
    private String orderNo;

    /** 用户ID */
    @TableField("user_id")
    private String userId;

    /** 支付金额 */
    @TableField("price")
    private BigDecimal price;

    /** 支付时间 */
    @TableField("paid_time")
    private LocalDateTime paidTime;

    /** 创建时间 */
    @TableField("create_time")
    private LocalDateTime createTime;

    /** 修改时间 */
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public LocalDateTime getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(LocalDateTime paidTime) {
        this.paidTime = paidTime;
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
