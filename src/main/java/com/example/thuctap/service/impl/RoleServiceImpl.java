package com.example.thuctap.service.impl;

import com.example.thuctap.dto.RoleDTO;
import com.example.thuctap.entity.Role;
import com.example.thuctap.repository.RoleRepository;
import com.example.thuctap.service.RoleService;
import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "role")
            }
    )
    public void create(RoleDTO roleDTO) {
        Role role = new ModelMapper().map(roleDTO,Role.class);
        role.setName(roleDTO.getName());
        roleRepository.save(role);
        //trả về id sau khi tạo
        roleDTO.setId(role.getId());
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "role")
    public void update(RoleDTO roleDTO) {
        Role currentRole = roleRepository.findById(roleDTO.getId()).orElseThrow(NoResultException::new);
        currentRole.setName(roleDTO.getName());
        roleRepository.save(currentRole);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "role",key = "#id")
    public void delete(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleDTO> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        return roles.stream().map(r ->convertoDTO(r)).collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "role",key = "#id")
    public RoleDTO getById(int id) {
        Role role = roleRepository.findById(id).orElseThrow(NoResultException::new);
        return convertoDTO(role);
    }
    public RoleDTO convertoDTO(Role role){
        return new ModelMapper().map(role,RoleDTO.class);
    }
}
