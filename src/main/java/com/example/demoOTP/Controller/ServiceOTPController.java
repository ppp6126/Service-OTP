package com.example.demoOTP.Controller;

import com.example.demoOTP.Config.MD5;
import com.example.demoOTP.Config.Request.otprequest;
import com.example.demoOTP.Model.ServiceOTP;
import com.example.demoOTP.Model.Owner;
import com.example.demoOTP.Service.ServiceOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ServiceOTPController {
    @Autowired
    private ServiceOTPService otpService;
    @Autowired
    private MD5 md5 ;

    @GetMapping("/randomOTP")
    public ServiceOTP RequestServiceOTP(@RequestHeader String Secret , @RequestBody otprequest request) throws Exception {
        ServiceOTP numOTP = new ServiceOTP();
        String email = request.getEmail();
        String tal = request.getNumberPhone();
        String nameServie = request.getNameService();

        Owner owner = otpService.getUserByNameService(nameServie);
        if(owner == null ){
            throw new Exception("No data Owner");
        }

        String reqid = "123";

        String type = "";
        String data = "";
        if(email != null && email != ""){
            type = "email" ;
            data = email ;
        }else{
            type = "phone_number" ;
            data = tal ;
        }

        if(Secret == null && Secret == "" ){
            throw new Exception("No Secretkey in Header");
        }else{
            numOTP = otpService.randomCode(type , data , nameServie , reqid);
        }

        return numOTP;
    }

    @PostMapping("/CheckOTP")
    public String CheckOTP(@RequestBody ServiceOTP serviceOTP ){
        int code = serviceOTP.getOtpCode();
        String reference = serviceOTP.getReferenceCode();
      return otpService.CheckTimeOTP(code , reference);
    }

    @PostMapping("/hashMD5")
    public String HashMD5(@RequestBody Owner owner){
        String SecretKey = owner.getSecretKey();
        String nameService = owner.getNameService();

        String hashSecretKey = md5.getMd5(SecretKey);

        owner = otpService.getUserByNameService(nameService);

        String key = md5.getMd5(owner.getSecretKey());

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
