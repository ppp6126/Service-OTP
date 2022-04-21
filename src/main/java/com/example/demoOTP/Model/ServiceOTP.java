package com.example.demoOTP.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="serviceOTP")
@Data
public class ServiceOTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date crateDate ;
    private Date compleseDate ;
    private Calendar expiredDate ;
    private String otpCode ;
    private String referenceCode ;
    private String uuId;
    private String reqId ;
    private String email ;
    private String tel ;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner = new Owner() ;

    public ServiceOTP() {
    }

    public ServiceOTP(Date crateDate, Date compleseDate, Calendar expiredDate, String otpCode, String referenceCode, String uuId, String reqId) {
        this.crateDate = crateDate;
        this.compleseDate = compleseDate;
        this.expiredDate = expiredDate;
        this.otpCode = otpCode;
        this.referenceCode = referenceCode;
        this.uuId = uuId;
        this.reqId = reqId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public Date getCompleseDate() {
        return compleseDate;
    }

    public void setCompleseDate(Date compleseDate) {
        this.compleseDate = compleseDate;
    }

    public Calendar getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Calendar expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}

