package com.example.thuctap.dto;

import lombok.Data;

@Data
public class SearchDTO {
    private String keyword;
    private Integer currentPage;
    private Integer size;
    private Integer offset;
    private String sortedField;

    //không cần cons cũng ok
//    public SearchDTO(int currentPage, int size) {
//        this.currentPage = 0;
//        this.size = 5;
//    }
}
