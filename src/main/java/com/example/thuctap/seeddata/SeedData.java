package com.example.thuctap.seeddata;

import com.example.thuctap.dto.UserDTO;
import com.example.thuctap.entity.User;
import com.example.thuctap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Component
//public class SeedData implements CommandLineRunner {
//    @Autowired
//    UserRepository userRepository;
//    @Override
//    public void run(String... args) throws Exception {
//        List<User> users = new ArrayList<>();
//        users.add(new User("abcg","ffgdh","69696969","abc@gmail.com","MALE"));
//        users.add(new User("abcg","ffgdh","69696969","abc@gmail.com","MALE"));
//        users.add(new User("abcg","ffgdh","69696969","abc@gmail.com","MALE"));
//        users.add(new User("abcg","ffgdh","69696969","abc@gmail.com","MALE"));
//        users.add(new User("abcg","ffgdh","69696969","abc@gmail.com","MALE"));
//        users.add(new User("abcg","ffgdh","69696969","abc@gmail.com","MALE"));
//        userRepository.saveAll(users);
//    }
//}
