package com.example.demoOTP.Config.Request;

public class otprequest {
    private String email ;
    private String numberPhone ;
    private String nameService;
    private String reqid ;
    public otprequest() {
    }

    public otprequest(String email, String nameService, String reqid) {
        this.email = email;
        this.nameService = nameService;
        this.reqid = reqid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }
}
