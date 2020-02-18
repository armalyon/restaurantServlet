package ua.restaurant.srvlt.dto.pagination;

import java.util.List;

public class Page<E> {
    private int totalRecords;
    private int currentPage;
    private int recordsPerPage;
    private List<E> records;
    private int totalPages;

    public Page() {
    }

    public Page(int totalRecords, int currentPage, int recordsPerPage, List<E> records) {
        this.totalRecords = totalRecords;
        this.currentPage = currentPage;
        this.recordsPerPage = recordsPerPage;
        this.records = records;
        totalPages = totalPages();
    }

    private int totalPages() {
        int total;
        if (totalRecords > 0) {
            total = (int) Math.ceil((double) totalRecords / recordsPerPage);
        } else total = 1;
        return total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public List<E> getRecords() {
        return records;
    }

    public void setRecords(List<E> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalRecords=" + totalRecords +
                ", currentPage=" + currentPage +
                ", recordsPerPage=" + recordsPerPage +
                ", totalPages=" + totalPages +
                ", records=" + records +
                '}';
    }
}
