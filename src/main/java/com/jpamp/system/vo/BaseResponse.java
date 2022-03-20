package com.jpamp.system.vo;

/**
 * @author xyb
 * @Description
 * @Date 2022/3/20 18:38
 */
public class BaseResponse<T> {

    private PagingResponse page;

    private T data;

    public PagingResponse getPage() {
        return page;
    }

    public void setPage(PagingResponse page) {
        this.page = page;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
