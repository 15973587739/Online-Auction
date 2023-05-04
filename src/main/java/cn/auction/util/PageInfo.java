package cn.auction.util;

import java.util.List;

/**
 * @Author: SIYU
 * @Description: TODO
 * @DateTime: 2023/5/4 11:50
 **/
public class PageInfo<T> {
    public static final int PAGE_SIZE = 5;
    //页面大小每页显示的记录数
    private int pageSize = 5;

    //总页数
    private int PageCount = 0;
    //记录总数
    private int Count;
    //当前页数
    private int PageNo = 1;
    //每页微博集合
    private List<T> List;


    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        if (count > 0) {
            this.Count = count;
            //计算总页数
            PageCount = this.Count % pageSize == 0 ?
                    (this.Count / pageSize) : (this.Count / pageSize + 1);
        }
    }

    public int getPageNo() {
        return PageNo;
    }

    public void setPageNo(int pageNo) {
        if (PageNo > 0) {
            this.PageNo = pageNo;
        }
    }

    public List<T> getList() {
        return List;
    }

    public void setList(List<T> list) {
        List = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
