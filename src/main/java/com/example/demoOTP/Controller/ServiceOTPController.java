package com.example.demoOTP.Controller;

import com.example.demoOTP.Config.MD5;
import com.example.demoOTP.Model.ServiceOTP;
import com.example.demoOTP.Model.User;
import com.example.demoOTP.Service.ServiceOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceOTPController {
    @Autowired
    private ServiceOTPService otpService;
    @Autowired
    private MD5 md5 ;

    @Autowired
    public void AutoCheckOTPTime(){
        otpService.AutoCheckOTPTime();
    }

    @GetMapping("/randomOTP")
    public ServiceOTP RequestServiceOTP(@RequestHeader String Secret) throws Exception {
        ServiceOTP numOTP = new ServiceOTP();

        if(Secret == null && Secret == "" ){
            throw new Exception("No Secretkey in Header");
        }else{
            numOTP = otpService.randomCode();
        }

        return numOTP;
    }

    @PostMapping("/RequestSecretKey")
    public String RequestSecretKey(@PathVariable String username ) {

        return "" ;
    }

    @PostMapping("/CheckOTP")
    public String CheckOTP(@RequestParam int code ){
      return otpService.CheckTimeOTP(code);
    }

    @PostMapping("/hashMD5")
    public String HashMD5(@RequestParam String SecretKey ,
                          @RequestParam String username){
        String hashSecretKey = md5.getMd5(SecretKey);
        User user = otpService.getUserByUsername(username);
        String key = md5.getMd5(user.getSecretKey().getSecretKey());

        String message = "" ;

        if(hashSecretKey.equals(key)){
             message = "Ok => key : "+ hashSecretKey  ;
        }else{
             message = "No => key : "+ hashSecretKey  ;
        }
        System.out.println(message );

        return message ;
    }
}
