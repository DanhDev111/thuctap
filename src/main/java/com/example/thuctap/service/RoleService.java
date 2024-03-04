package com.example.thuctap.service;

import com.example.thuctap.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    void create(RoleDTO roleDTO);
    void update(RoleDTO roleDTO);
    void delete(int id);
    List<RoleDTO> getAll();
    RoleDTO getById(int id);

}
