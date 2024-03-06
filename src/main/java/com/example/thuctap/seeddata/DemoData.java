package com.example.thuctap.seeddata;

import com.example.thuctap.dto.UserDTO;
import com.example.thuctap.entity.Role;
import com.example.thuctap.entity.User;
import com.example.thuctap.repository.RoleRepository;
import com.example.thuctap.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;


//@Component
//@Slf4j
//public class DemoData implements CommandLineRunner {
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            User user = new User();
//            user.setName("User " + i);
//            user.setUsername("Username" + i);
//            user.setPassword(new BCryptPasswordEncoder().encode("testdemodata" + i));
//            user.setGender(user.getGender());
//            user.setEmail("user" + i + "@gmail.com");
//            userRepository.save(user);
//        }
//
//        log.info("INSERT User data completed");
//    }
//}

