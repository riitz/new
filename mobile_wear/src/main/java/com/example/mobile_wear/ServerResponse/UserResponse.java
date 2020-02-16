package com.example.mobile_wear.ServerResponse;

public class UserResponse {

    private String Status;
    private String token;

    public UserResponse(String status, String token) {
        Status = status;
        this.token = token;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
