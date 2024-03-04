package com.example.thuctap.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleDTO {
    private int id;

    @Size(min = 4,max = 20)
    @NotBlank(message = "not.blank")
    private String name;
}
