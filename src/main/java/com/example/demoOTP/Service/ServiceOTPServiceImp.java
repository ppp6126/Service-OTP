package com.example.demoOTP.Service;

import com.example.demoOTP.Config.SendEmail;
import com.example.demoOTP.Model.ServiceOTP;
import com.example.demoOTP.Model.Owner;
import com.example.demoOTP.Repository.ServiceOTPRepository;
import com.example.demoOTP.Repository.OwnerRepository;
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

    @Override
    public ServiceOTP randomCode(String type , String data , String serviceName , String reqid) throws Exception {
        String message = "";
        ServiceOTP otp = new ServiceOTP();
        Owner owner = new Owner();

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

        owner = userRepo.findByNameService(serviceName);
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
                int key = ck.getOtpCode();
                if(key != code){
                    otp.setCrateDate(date);
                    otp.setOtpCode(code);
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
            otp.setOtpCode(code);
            otp.setExpiredDate(calendarExpired);
            otp.setUuId(String.valueOf(uuid));
            otp.setReqId(reqid);
            serviceOTPRepo.save(otp);
            sendEmail.SendEmail(data,"OTP Code Reset Data", "Code OTP : "+String.valueOf(code));
            message = "saved successfully";
        }

        if(message.equals("saved successfully")){
            return otp;
        }else {
            throw new Exception("pless random key again");
        }
    }

    @Override
    public Owner getUserByNameService(String username) {
        return userRepo.findByNameService(username);
    }

    @Override
    public String CheckTimeOTP(int code, String reference) {
        ServiceOTP servicedata = serviceOTPRepo.findByOtpCode(code);

        Calendar date = servicedata.getExpiredDate();
        String ref = servicedata.getReferenceCode();

        int ExpirationTime = date.getTime().getMinutes();

        Calendar calendar = Calendar.getInstance();
        int TimeNow = calendar.getTime().getMinutes();

        if(ref.equals(reference)) {
            if (TimeNow > ExpirationTime) {
                Date dateComplese = new Date();
                servicedata.setCompleseDate(dateComplese);
                serviceOTPRepo.save(servicedata);

                return "OK";
            } else {
                return "Code Expired";
            }
        }else{
            return "Reference Code Not Complish";
        }
    }

    @Override
    public List<Owner> getAllUSer() {
        return userRepo.findAll();
    }
}
//2022-04-08 22:16:57.0
