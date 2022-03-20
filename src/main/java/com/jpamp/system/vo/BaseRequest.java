package com.jpamp.system.vo;

/**
 * @author xyb
 * @Description
 * @Date 2022/3/20 18:38
 */
public class BaseRequest<T> {

    private PagingRequest page;

    private T params;

    public PagingRequest getPage() {
        return page;
    }

    public void setPage(PagingRequest page) {
        this.page = page;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
