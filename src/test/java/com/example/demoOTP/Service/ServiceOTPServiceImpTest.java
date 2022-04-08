package com.example.demoOTP.Service;

import com.example.demoOTP.Model.User;
import com.example.demoOTP.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ServiceOTPServiceImpTest {
    @Autowired
    UserRepository userRepository ;
    @Test
    public void randomCode() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long l = timestamp.getTime() + 300000;
        timestamp = new Timestamp(l);
        System.out.println(timestamp);

//        User user = new User("Phiphatpong", 23, "M", "City", "Phiphatpong.n@gmail.com", "0650230101", "ppp", "1234");
//        User user2 = new User("Staporn", 23, "M", "City", "Phiphatpong.n@gmail.com", "0650230101", "ktp", "1234");
//        User user3 = new User("Kanokporn", 23, "F", "City", "Phiphatpong.n@gmail.com", "0650230101", "knp", "1234");
//        User user4 = new User("Pichet", 23, "F", "City", "Phiphatpong.n@gmail.com", "0650230101", "pct", "1234");
//        userRepository.save(user);
//        userRepository.save(user2);
//        userRepository.save(user3);
//        userRepository.save(user4);


    }

}