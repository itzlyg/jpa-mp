package com.jpamp.system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xyb
 * @Description
 * @Date 2022/3/20 18:38
 */
public class PagingResponse {

    /** 总页数 */
    private long pages;
    /** 当前页数 */
    @JsonProperty("page_num")
    private long pageNum;
    /** 每页的数量 */
    @JsonProperty("page_size")
    private long pageSize;
    /** 总数量 */
    @JsonProperty("total_num")
    private long totalNum;

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }
}
