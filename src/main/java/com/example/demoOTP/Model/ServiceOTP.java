package com.example.demoOTP.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="serviceOTP")
@Data
public class ServiceOTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceOTP_id;
    private Date date ;
    private Timestamp expired ;
    private int code ;
    private String status ;

    public ServiceOTP() {
    }

    public ServiceOTP(Long serviceOTP_id, Date date, Timestamp expired, int code, String status) {
        this.serviceOTP_id = serviceOTP_id;
        this.date = date;
        this.expired = expired;
        this.code = code;
        this.status = status;
    }

    public Long getServiceOTP_id() {
        return serviceOTP_id;
    }

    public void setServiceOTP_id(Long serviceOTP_id) {
        this.serviceOTP_id = serviceOTP_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

