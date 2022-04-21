package com.example.demoOTP.Service;

import com.example.demoOTP.Config.Request.otprequest;
import com.example.demoOTP.Model.Owner;

import java.util.List;

public interface ServiceOTPService {

    Owner getUserByNameService(String username);

    Object CheckTimeOTP(String code, String reference, String uuid);

    List<Owner> getAllUSer();

    Object randomCode(String secret, otprequest request)throws Exception;
}
