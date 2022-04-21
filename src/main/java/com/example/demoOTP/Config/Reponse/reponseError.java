package com.example.demoOTP.Config.Reponse;

public class reponseError {
    private String Error;
    private String Message;

    public reponseError() {
    }

    public reponseError(String error, String message) {
        Error = error;
        Message = message;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
