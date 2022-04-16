package com.example.demoOTP.Service;

import com.example.demoOTP.Model.ServiceOTP;
import com.example.demoOTP.Model.Owner;

import java.util.List;

public interface ServiceOTPService {
    ServiceOTP randomCode(String type, String data, String serviceName, String reqid) throws Exception;

    Owner getUserByNameService(String username);

    String CheckTimeOTP(int code, String reference);

    List<Owner> getAllUSer();
}
