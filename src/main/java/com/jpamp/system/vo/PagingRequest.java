package com.jpamp.system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xyb
 * @Description
 * @Date 2022/3/20 18:38
 */
public class PagingRequest {
    /** 分页当前页 */
    @JsonProperty("page_num")
    private Integer pageNum;

    /** 分页大小 */
    @JsonProperty("page_size")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
