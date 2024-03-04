package com.example.thuctap.controller;

import com.example.thuctap.dto.ResponseDTO;
import com.example.thuctap.dto.RoleDTO;
import com.example.thuctap.entity.Role;
import com.example.thuctap.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/list")
    public ResponseDTO<List<RoleDTO>> listRole(){
        List<RoleDTO> roleDTOS = roleService.getAll();
        return ResponseDTO.<List<RoleDTO>>builder()
                .status(200)
                .msg("OK")
                .data(roleDTOS)
                .build();
    }

    @PostMapping("/")
    public ResponseDTO<Void> create(@RequestBody @Valid RoleDTO roleDTO){
        roleService.create(roleDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("OK")
                .build();
    }
    @PutMapping("/")
    public ResponseDTO<Void> edit(@RequestBody RoleDTO roleDTO){
        roleService.update(roleDTO);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("OK")
                .build();
    }

    @GetMapping("/")
    public ResponseDTO<RoleDTO> getById(@RequestParam("id") int id){
        return ResponseDTO.<RoleDTO>builder()
                .status(200)
                .msg("OK")
                .data(roleService.getById(id))
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") int id){
        roleService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("OK")
                .build();
    }

}
