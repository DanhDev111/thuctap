package com.example.thuctap.service.impl;

import com.example.thuctap.dto.PageDTO;
import com.example.thuctap.dto.RoleDTO;
import com.example.thuctap.dto.SearchDTO;
import com.example.thuctap.dto.UserDTO;
import com.example.thuctap.entity.GenderName;
import com.example.thuctap.entity.Role;
import com.example.thuctap.entity.User;
import com.example.thuctap.repository.RoleRepository;
import com.example.thuctap.repository.UserRepository;
import com.example.thuctap.service.UserService;
import com.google.gson.JsonParseException;
import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "users-search")
    })
    public void create(UserDTO userDTO) {
        User user = new ModelMapper().map(userDTO, User.class);
        // Convert gender display name to GenderName enum
        user.setGender(userDTO.getGender().getDisplayName());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

//        List<Role> roles = new ArrayList<>();
//        for (RoleDTO roleDTO : userDTO.getRoles()) {
//            Role role = roleRepository.findById(roleDTO.getId()).orElse(null);
//            if (role != null) {
//                roles.add(role);
//            }
//        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) {
        User currentUser = userRepository.findById(userDTO.getId()).orElseThrow(NoResultException::new);
        // Convert gender display name to GenderName enum

        if (currentUser!=null){
            currentUser = new ModelMapper().map(userDTO,User.class);
            currentUser.setGender(userDTO.getGender().getDisplayName());
            currentUser.setPassword(new BCryptPasswordEncoder().encode(currentUser.getPassword()));

        }
        userRepository.save(currentUser);
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "users", key = "#id", allEntries = true), //allEntries là mình xóa hết
            @CacheEvict(cacheNames = "users-search",allEntries = true)
    })
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "users",key = "#id") //nếu để lưu vào trong bộ nhớ cache thì mình dùng cacheable
    public UserDTO getById(int id) {
        User user = userRepository.findById(id).orElseThrow(NoResultException::new);
        return convertoDTO(user);
    }

    @Override
    @CacheEvict(cacheNames = "users")
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        return users.stream().map(u -> convertoDTO(u)).collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "users-search")
    public PageDTO<UserDTO> searchByNameOrUserName(SearchDTO searchDTO) {
        PageRequest pageable = PageRequest.of(searchDTO.getCurrentPage(),searchDTO.getSize());
        Page<User> userPage = userRepository.searchByNameOrUsername("%"+searchDTO.getKeyword()+"%",pageable);

        if (searchDTO.getKeyword()==null){
            searchDTO.setKeyword("");
        }
        if (searchDTO.getSize()==null){
            searchDTO.setSize(5);
        }
        if (searchDTO.getCurrentPage() ==null){
            searchDTO.setCurrentPage(0);
        }
        List<UserDTO> userDTOS = userPage.get().map(r -> convertoDTO(r)).collect(Collectors.toList());
        return PageDTO.<UserDTO>builder()
                .totalPages(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements())
                .data(userDTOS)
                .build();
    }

    public UserDTO convertoDTO(User user){
        return new ModelMapper().map(user,UserDTO.class);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null){
            throw new UsernameNotFoundException("Not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles();
        for (Role role : userEntity.getRoles()) {
            //truyền vai trò về role trong security
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(
                username, userEntity.getPassword(), authorities);
    }
    public UserDTO findByUsername(String username) { // java8, optinal
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new NoResultException();
        return new ModelMapper().map(user, UserDTO.class);
    }
}
