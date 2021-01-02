package bean;

import java.util.List;

public class Page<T> {
    // 当前页查询第几页
    private Integer currentPage;

    // 每页几页
    private Integer pageSize;

    //上一页
    private Integer beforePage;

    //下一页
    private Integer afterPage;

    // 总行数
    private Integer totalRows;

    // 总页数
    private Integer totalPages;

    private List<T> list;

    public Page() {
    }

    public Page(Integer currentPage, Integer pageSize, Integer totalRows, List<T> list) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRows = totalRows;
        this.list = list;

        // 一共多少页
        this.totalPages = this.totalRows % this.pageSize == 0 ? this.totalRows / this.pageSize : this.totalRows / this.pageSize + 1;

        // 上一页
        this.beforePage = this.currentPage - 1 <= 0 ? 1 : this.currentPage - 1;

        // 下一页
        this.afterPage = this.currentPage + 1 >= this.totalPages ? this.totalPages : this.currentPage + 1;
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

    public Integer getBeforePage() {
        return beforePage;
    }

    public void setBeforePage(Integer beforePage) {
        this.beforePage = beforePage;
    }

    public Integer getAfterPage() {
        return afterPage;
    }

    public void setAfterPage(Integer afterPage) {
        this.afterPage = afterPage;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
