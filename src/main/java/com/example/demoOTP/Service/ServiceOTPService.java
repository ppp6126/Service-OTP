package com.example.demoOTP.Service;

import com.example.demoOTP.Model.ServiceOTP;
import com.example.demoOTP.Model.User;

public interface ServiceOTPService {
    ServiceOTP randomCode() throws Exception;

    User getUserByUsername(String username);

    String CheckTimeOTP(int code);

    void AutoCheckOTPTime();
}
