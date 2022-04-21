package com.example.demoOTP.Config.Reponse;

public class responseSuccessfully {
    private String message;
    private String uuid ;
    private String referenceCode ;

    public responseSuccessfully() {
    }

    public responseSuccessfully(String message, String uuid, String referenceCode) {
        this.message = message;
        this.uuid = uuid;
        this.referenceCode = referenceCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
}
