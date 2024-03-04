package com.example.thuctap.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageDTO<T> {
    private int totalPages;
    private long totalElements;
    private List<T> data;
}
