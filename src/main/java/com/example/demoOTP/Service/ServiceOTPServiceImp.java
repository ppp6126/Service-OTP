package com.example.demoOTP.Service;

import com.example.demoOTP.Model.ServiceOTP;
import com.example.demoOTP.Model.User;
import com.example.demoOTP.Repository.ServiceOTPRepository;
import com.example.demoOTP.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ServiceOTPServiceImp implements ServiceOTPService{

    @Autowired
    ServiceOTPRepository serviceOTPRepo ;
    @Autowired
    UserRepository userRepo;

    @Override
    public ServiceOTP randomCode() throws Exception {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);
        System.out.println(formatted);
        int code = Integer.valueOf(formatted);

        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long l = timestamp.getTime() + 300000;
        timestamp = new Timestamp(l);
        System.out.println(timestamp);

        ServiceOTP otp = new ServiceOTP();
        List<ServiceOTP> listOTP = serviceOTPRepo.findAll();
        String message = "";

        if(listOTP.size() > 0){
            for(ServiceOTP ck :listOTP){
                int key = ck.getCode();
                if(key != code){
                    otp.setStatus("Not expired");
                    otp.setDate(date);
                    otp.setCode(code);
                    otp.setExpired(timestamp);
                    serviceOTPRepo.save(otp);
                    message = "saved successfully";
                    break;
                }
            }
        }else {
            otp.setStatus("Not expired");
            otp.setDate(date);
            otp.setCode(code);
            otp.setExpired(timestamp);
            serviceOTPRepo.save(otp);
            message = "saved successfully";
        }

        if(message.equals("saved successfully")){
            return otp;
        }else {
            throw new Exception("pless random key again");
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public String CheckTimeOTP(int code) {
        ServiceOTP serviceOTP = serviceOTPRepo.getServiceOTPByCode(code);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp timeOTP = serviceOTP.getExpired();

        if(timestamp == timeOTP){
            return "OK";
        }else{
            return "Code Expired";
        }
    }

    @Override
    public void AutoCheckOTPTime() {

        List<ServiceOTP> listOTP = serviceOTPRepo.findByStatus("Not Expired");
        if(listOTP.size() > 0){
            for(ServiceOTP ck :listOTP){
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Timestamp timeOTP = ck.getExpired();
                int m = timeOTP.getMinutes();
                int Minutesnow = timestamp.getMinutes();

                if(Minutesnow > m){
                    ck.setStatus("Expired");
                    serviceOTPRepo.save(ck);
                }else{
                    ck.setStatus("Not Expired");
                    serviceOTPRepo.save(ck);
                }

            }
        }
    }
}
//2022-04-08 22:16:57.0
