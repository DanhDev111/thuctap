package com.example.thuctap.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private int status;
    private String msg;
    private T data;

    public ResponseDTO(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
