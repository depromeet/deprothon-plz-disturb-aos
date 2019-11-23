package com.depromeet.plzdisturb.data.dto;

public class LoginRequest {
    private String accessToken;
    private String deviceToken;

    public LoginRequest(String accessToken, String deviceToken) {
        this.accessToken = accessToken;
        this.deviceToken = deviceToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
