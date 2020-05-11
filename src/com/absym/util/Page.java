package com.absym.util;


public class Page {
    private int page;
    private int limit;
    private int offset;

    public Page() {
    }


    public Page(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        this.offset = (page - 1) * limit;
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = (page - 1) * limit;
    }
}
