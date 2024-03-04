package com.example.thuctap.controller;


import com.example.thuctap.dto.PageDTO;
import com.example.thuctap.dto.ResponseDTO;
import com.example.thuctap.dto.SearchDTO;
import com.example.thuctap.dto.UserDTO;
import com.example.thuctap.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/search")
    public ResponseDTO<PageDTO<UserDTO>> searchUserByName(@RequestBody SearchDTO searchDTO){
        PageDTO<UserDTO> pageDTO = userService.searchByNameOrUserName(searchDTO);
        return ResponseDTO.<PageDTO<UserDTO>>builder()
                .status(200)
                .msg("OK")
                .data(pageDTO)
                .build();
    }

    @PostMapping("/")
    public ResponseDTO<Void> newUser(@RequestBody @Valid UserDTO userDTO){
        userService.create(userDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("Create successfully!!")
                .build();
    }

    @PutMapping("/")
    public ResponseDTO<Void> updateUser(@RequestBody @Valid UserDTO userDTO){
        userService.update(userDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("OK")
                .build();
    }

    @GetMapping("/")
    public ResponseDTO<UserDTO> getById(@RequestParam("id") int id){
        return ResponseDTO.<UserDTO>builder()
                .status(200)
                .msg("OK")
                .data(userService.getById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") int id){
        userService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("OK")
                .build();
    }
    @PostMapping("/list")
    public ResponseDTO<List<UserDTO>> list() {
        List<UserDTO> usersDTO = userService.getAll();
        return ResponseDTO.<List<UserDTO>>builder()
                .status(200)
                .msg("OK")
                .data(usersDTO).build();
    }

}
