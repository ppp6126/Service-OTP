package com.example.demoOTP.Repository;

import com.example.demoOTP.Model.ServiceOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceOTPRepository extends JpaRepository<ServiceOTP , Long> {

    Optional<ServiceOTP> findByReferenceCodeEquals(String referencekey);

    ServiceOTP findByOtpCode(int code);

}
