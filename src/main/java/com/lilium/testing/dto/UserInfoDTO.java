package com.lilium.testing.dto;

public class UserInfoDTO {
    private String userId;
    private String address;
    private String phoneNumber;

    public UserInfoDTO(String userId, String address, String phoneNumber) {
        this.userId = userId;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
