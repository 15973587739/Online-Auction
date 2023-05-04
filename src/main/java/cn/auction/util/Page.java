package cn.auction.util;

import java.util.List;

public class Page<T> {
    //总页数
    private int totalPageCount = 0;
    //页面大小每页显示的记录数
    private int pageSize = 5;
    //记录总数
    private int totalCount;
    //当前页数
    private int currPageNo = 1;
    //每页微博集合
    private List<T> List;

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            //计算总页数
            totalPageCount = this.totalCount % pageSize == 0 ?
                    (this.totalCount / pageSize) : (this.totalCount / pageSize + 1);
        }

    }

    public int getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(int currPageNo) {
        if (currPageNo > 0) {
            this.currPageNo = currPageNo;
        }
    }

    public java.util.List<T> getList() {
        return List;
    }

    public void setList(java.util.List<T> list) {
        List = list;
    }
}
