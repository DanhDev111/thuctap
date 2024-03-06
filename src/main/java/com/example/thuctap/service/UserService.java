package com.example.thuctap.service;

import com.example.thuctap.dto.PageDTO;
import com.example.thuctap.dto.SearchDTO;
import com.example.thuctap.dto.UserDTO;

import java.util.List;

public interface UserService {
    void create(UserDTO userDTO);
    void update(UserDTO userDTO);
    void delete(int id);
    UserDTO getById(int id);
    List<UserDTO> getAll();

    PageDTO<UserDTO> searchByNameOrUserName(SearchDTO searchDTO);
    public UserDTO findByUsername(String username);

}
