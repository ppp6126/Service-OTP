package com.example.demoOTP.Config.Request;

public class otprequest {
    private String email ;
    private String numberPhone ;
    private String nameService;

    public otprequest() {
    }

    public otprequest(String email, String numberPhone, String nameService) {
        this.email = email;
        this.numberPhone = numberPhone;
        this.nameService = nameService;
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
}
