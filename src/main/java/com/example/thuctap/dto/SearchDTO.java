package com.example.thuctap.dto;

import lombok.Data;

@Data
public class SearchDTO {
    private String keyword;
    private Integer currentPage;
    private Integer size;

    public SearchDTO(int currentPage, int size) {
        this.currentPage = 0;
        this.size = 5;
    }
}
