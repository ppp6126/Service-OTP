package com.example.demoOTP.Repository;

import com.example.demoOTP.Model.ServiceOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceOTPRepository extends JpaRepository<ServiceOTP , Long> {


    ServiceOTP getServiceOTPByCode(int code);

    List<ServiceOTP> findByStatus(String status);
}
