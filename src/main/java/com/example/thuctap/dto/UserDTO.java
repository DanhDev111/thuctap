package com.example.thuctap.dto;

import com.example.thuctap.entity.GenderName;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;

    private String name;
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    private GenderName gender;
    private List<RoleDTO> roles;

    //@JsonFormat(timezone)
}
