package com.ncu.edu.pojo;

public class Page {
    private Integer currentPage;
    private Integer pageSize;
    private Integer count;
    private String queryString;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Page(Integer currentPage, Integer pageSize, Integer count, String queryString) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.count = count;
        this.queryString = queryString;
    }

    public Page(Integer currentPage, Integer pageSize, Integer count) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Page() {
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Page(Integer currentPage, Integer pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
