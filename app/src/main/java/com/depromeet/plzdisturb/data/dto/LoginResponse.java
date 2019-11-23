package com.depromeet.plzdisturb.data.dto;

public class LoginResponse {

    private String accesstoken;

    public LoginResponse() {

    }

    public LoginResponse(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }
}
