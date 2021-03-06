package com.kanjia.basic;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private Integer total;
    private List<T> rows;

    public PageInfo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStart() {
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 10;
        return (pageNum - 1) * pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum <= 1)
            this.pageNum = 1;
        else
            this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize >= 10)
            this.pageSize = 10;
        else
            this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", rows=" + rows +
                '}';
    }
}