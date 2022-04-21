package com.example.demoOTP.Controller;

import com.example.demoOTP.Config.MD5;
import com.example.demoOTP.Config.Request.otprequest;
import com.example.demoOTP.Model.Owner;
import com.example.demoOTP.Model.ServiceOTP;
import com.example.demoOTP.Service.ServiceOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceOTPController {
    @Autowired
    private ServiceOTPService otpService;
    @Autowired
    private MD5 md5 ;

    @GetMapping("/randomOTP")
    public Object RequestServiceOTP(@RequestHeader String Secret , @RequestBody otprequest request) throws Exception {
        Object numOTP = otpService.randomCode(Secret , request );

        return numOTP;

    }

    @PostMapping("/CheckOTP")
    public Object CheckOTP(@RequestBody ServiceOTP serviceOTP ){
        String code = serviceOTP.getOtpCode();
        String reference = serviceOTP.getReferenceCode();
        String uuid = serviceOTP.getUuId();

      return otpService.CheckTimeOTP(code , reference , uuid);
    }

    @PostMapping("/hashMD5")
    public String HashMD5(@RequestBody Owner owner){
        String SecretKey = owner.getSecretKey();
        String nameService = owner.getNameService();
        String reqid = "124";

        String hashSecretKey = md5.getMd5(SecretKey+reqid);

        owner = otpService.getUserByNameService(nameService);

        String key = md5.getMd5(owner.getSecretKey()+reqid);

        String message = "" ;

        if(hashSecretKey.equals(key)){
             message = "Ok => key : "+ hashSecretKey  ;
        }else{
             message = "No => key : "+ hashSecretKey  ;
        }
        System.out.println(message );

        return message ;
    }

    @GetMapping("/getalloener")
    public List<Owner> getalloener(){
        return otpService.getAllUSer();
    }
}
