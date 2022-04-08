package com.example.demoOTP.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "secretkey")
public class SecretKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SecretKey_id;
    private String SecretKey;

    public SecretKey() {
    }

    public SecretKey(Long secretKey_id, String secretKey) {
        SecretKey_id = secretKey_id;
        SecretKey = secretKey;
    }

    public Long getSecretKey_id() {
        return SecretKey_id;
    }

    public void setSecretKey_id(Long secretKey_id) {
        SecretKey_id = secretKey_id;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }
}
