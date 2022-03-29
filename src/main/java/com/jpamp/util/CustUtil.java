package com.jpamp.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jpamp.system.vo.BaseResponse;
import com.jpamp.system.vo.PagingRequest;
import com.jpamp.system.vo.PagingResponse;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.function.Function;

public class CustUtil {

    public static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomString(final int count) {
        return RandomStringUtils.random(count, CHARS);
    }

    public static String result() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static <T> BaseResponse<List<T>> page(PagingRequest requestPage, Function<Page<T>, IPage<T>> function) {
        int pageSize = requestPage.getPageSize();
        Page<T> p = new Page<>(requestPage.getPageNum(), pageSize);
        IPage<T> pageData = function.apply(p);
        BaseResponse<List<T>> response = new BaseResponse<>();
        List<T> datas = pageData.getRecords();
        PagingResponse page = new PagingResponse();
        long total = pageData.getTotal();
        Long totalPage = total / pageSize;
        if (total % pageSize != 0) {
            totalPage += 1;
        }
        // 总页数
        page.setPages(total);
        // 当前页数
        page.setPageNum(pageData.getCurrent());
        // 每页的数量
        page.setPageSize(pageSize);
        // 总数量
        page.setTotalNum(totalPage);
        response.setPage(page);
        response.setData(datas);
        return response;
    }
}
