package com.example.demoOTP.Service;

import com.example.demoOTP.Config.MD5;
import com.example.demoOTP.Model.Owner;
import com.example.demoOTP.Model.ServiceOTP;
import com.example.demoOTP.Repository.OwnerRepository;
import com.example.demoOTP.Repository.ServiceOTPRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@SpringBootTest
class ServiceOTPServiceImpTest2 {

//    @Autowired
//    ServiceOTPRepository serviceOTPRepo ;
    @Autowired
    OwnerRepository userRepo;
    @Autowired
    private MD5 md5 ;

//    @Test
//    public ServiceOTP randomCode() throws Exception {
//        return serviceOTP.randomCode("email","aooza12380@gmail.com","Phiphatpong");
//    }
//
//    @Test
//    public String CheckTimeOTP() throws Exception {
//        return serviceOTP.CheckTimeOTP(1234);
//    }

//    @Test
//    public Owner getUserByNameService() {
//        String username = "Phiphatpong" ;
//        return userRepo.findByNameService(username);
//    }

    @Test
    public List<Owner> getAllUSer() {
        return userRepo.findAll();
    }
}