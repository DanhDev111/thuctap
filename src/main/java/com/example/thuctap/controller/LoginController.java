package com.example.thuctap.controller;

import com.example.thuctap.dto.ResponseDTO;
import com.example.thuctap.dto.UserDTO;
import com.example.thuctap.jwt.JwtTokenService;
import com.example.thuctap.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginController {

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseDTO<String> login(HttpSession httpSession, @RequestParam("username") String username,
                                                            @RequestParam("password") String password
    ){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        return ResponseDTO.<String>builder()
                .status(200)
                .data(jwtTokenService.createToken(username))
                .msg("OK")
                .build();
    }
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public UserDTO me(Principal p){
        String username = p.getName();
        UserDTO user = userService.findByUsername(username);
        return user;
    }
}
