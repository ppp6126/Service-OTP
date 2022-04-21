package com.example.demoOTP.Service;

import com.example.demoOTP.Config.MD5;
import com.example.demoOTP.Config.Reponse.reponseError;
import com.example.demoOTP.Config.Reponse.responseSuccessfully;
import com.example.demoOTP.Config.Request.otprequest;
import com.example.demoOTP.Config.SendEmail;
import com.example.demoOTP.Model.Owner;
import com.example.demoOTP.Model.ServiceOTP;
import com.example.demoOTP.Repository.OwnerRepository;
import com.example.demoOTP.Repository.ServiceOTPRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ServiceOTPServiceImp implements ServiceOTPService{

    @Autowired
    ServiceOTPRepository serviceOTPRepo ;
    @Autowired
    OwnerRepository userRepo;
    @Autowired
    SendEmail sendEmail ;
    @Autowired
    private MD5 md5 ;

    reponseError reponseError = new reponseError();
    responseSuccessfully responseSuccessfully = new responseSuccessfully();
    @Override
    public Object randomCode(String secret, otprequest request) throws Exception {
        String email = request.getEmail();
        String nameServie = request.getNameService();
        String reqid = request.getReqid();

        String type = "";
        String data = "";

        reponseError reponseError = new reponseError();
        Owner owner = userRepo.findByNameService(nameServie);

        if(owner == null ){
            reponseError.setError("Error Owner");
            reponseError.setMessage("No data Owner");
            return reponseError;
        }else{
            if(email != null && email != ""){
                type = "email" ;
                data = email ;
            }else{
                reponseError.setError("Error Email");
                reponseError.setMessage("Email not entered or does not exist ");
                return reponseError;
            }
        }


        if(reqid != null || !reqid.equals("")){
            if(!secret.isEmpty()){
                String message = "" ;
                String key = md5.getMd5(owner.getSecretKey()+reqid);
                if(!secret.equals(key)){
                    reponseError.setError("Error Secretkey");
                    reponseError.setMessage("No Secretkey in Header");
                    return reponseError;
                }
            }
        }else{
            reponseError.setError("Error Request Id");
            reponseError.setMessage("Request Id Not Found");
            return reponseError;
        }

        String message = "";
        ServiceOTP otp = new ServiceOTP();

        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);
        System.out.println(formatted);
        int code = Integer.valueOf(formatted);
        System.out.println(code);

        int length = 5;
        boolean useLetters = true;
        boolean useNumbers = false;
        String referencekey = RandomStringUtils.random(length, useLetters, useNumbers);
        System.out.println(referencekey);

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
        Date date = new Date();
        String dt = String.valueOf(date);

        Calendar calendarExpired = Calendar.getInstance();
        String d = String.valueOf(calendarExpired.getTime().toInstant());
        String subdate = d.substring(0,10);
        String subtime = dt.substring(11 , 19);
        String[] strdate = subdate.split("-");
        String[] strtime = subtime.split(":");

        int day = Integer.parseInt(strdate[0]);
        int month = Integer.parseInt(strdate[1])-1;
        int year = Integer.parseInt(strdate[2]);
        int h = Integer.parseInt(strtime[0]);
        int m = Integer.parseInt(strtime[1])+5;
        int s = Integer.parseInt(strtime[2]);

        calendarExpired.set(day , month ,year , h , m , s);
        System.out.println(sdf.format(calendarExpired.getTime()));

        List<ServiceOTP> listOTP = serviceOTPRepo.findAll();
        Optional<ServiceOTP> Oreferencekey = serviceOTPRepo.findByReferenceCodeEquals(referencekey);

        owner = userRepo.findByNameService(nameServie);
        otp.setOwner(owner);

        if(!Oreferencekey.isPresent()){
            otp.setReferenceCode(referencekey);
        }

        if(type.equals("email")){
            otp.setEmail(data);
        }else{
            otp.setTel(data);
        }

        if(listOTP.size() > 0){
            for(ServiceOTP ck :listOTP){
                String key = ck.getOtpCode();
                if(!key.equals(code)){
                    otp.setCrateDate(date);
                    otp.setOtpCode(String.valueOf(code));
                    otp.setExpiredDate(calendarExpired);
                    otp.setUuId(String.valueOf(uuid));
                    otp.setReqId(reqid);
                    serviceOTPRepo.save(otp);
                    sendEmail.SendEmail(data,"OTP Code Reset Data", "Code OTP : "+String.valueOf(code));
                    message = "saved successfully";
                    break;
                }
            }
        }else {
            otp.setCrateDate(date);
            otp.setOtpCode(String.valueOf(code));
            otp.setExpiredDate(calendarExpired);
            otp.setUuId(String.valueOf(uuid));
            otp.setReqId(reqid);
            serviceOTPRepo.save(otp);
            sendEmail.SendEmail(data,"OTP Code Reset Data", "Code OTP : "+String.valueOf(code));
            message = "saved successfully";
        }

        if(message.equals("saved successfully")){
            responseSuccessfully successfully =  new responseSuccessfully("200 OK",otp.getUuId(),otp.getReferenceCode());
            return successfully;
        }else {
            throw new Exception("pless random key again");
        }
    }

    @Override
    public Owner getUserByNameService(String username) {
        return userRepo.findByNameService(username);
    }

    @Override
    public Object CheckTimeOTP(String code, String reference, String uuid) {

        if(code == null || code.equals("")){
            reponseError.setError("Error Code");
            reponseError.setMessage("did not enter the code ");
            return reponseError;
        }
        if(reference == null || reference.equals("")){
            reponseError.setError("Error reference");
            reponseError.setMessage("did not enter the reference code ");
            return reponseError;
        }
        if(uuid == null || uuid.equals("")){
            reponseError.setError("Error uuid");
            reponseError.setMessage("did not enter the uuid ");
            return reponseError;
        }
        ServiceOTP servicedata = serviceOTPRepo.findByOtpCode(code);
        Calendar date = servicedata.getExpiredDate();
        String ref = servicedata.getReferenceCode();
        String uid = servicedata.getUuId();
        int ExpirationTime = date.getTime().getMinutes();

        Calendar calendar = Calendar.getInstance();
        int TimeNow = calendar.getTime().getMinutes();

        if(ref.equals(reference)) {
            if(uid.equals(uuid)){
                if (TimeNow < ExpirationTime) {
                    Date dateComplese = new Date();
                    servicedata.setCompleseDate(dateComplese);
                    serviceOTPRepo.save(servicedata);
                    responseSuccessfully.setMessage("Use Code Successfully");
                    return responseSuccessfully;
                } else {
                    reponseError.setMessage("Code Expired");
                    return reponseError;
                }
            }else {
                reponseError.setError("Error uuid");
                reponseError.setMessage("Uuid mismatch");
                return reponseError;
            }
        }
        reponseError.setError("Error Reference Code");
        reponseError.setMessage("Reference Code Not Mismatch");
        return reponseError;
    }

    @Override
    public List<Owner> getAllUSer() {
        return userRepo.findAll();
    }
}
//2022-04-08 22:16:57.0
