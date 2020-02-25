package com.weds.site.base;

import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class PageSearchRespEntity<T> extends CustomPageSearch {
    private int totalCount;

    @ColumnProperties(aes = true)
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
