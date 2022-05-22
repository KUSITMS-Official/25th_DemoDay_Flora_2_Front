package com.example.flora.request;
/*
{
        "email": "string",
        "loginType": "CUSTOMER",
        "password": "string",
        "phone": "string",
        "username": "string"
}
 */
public class SignUpRequest {

    private String email;
    private String loginType;
    private String password;
    private String phone;
    private String username;

    public SignUpRequest(String email, String password, String phone, String username) {
        this.email = email;
        this.loginType = "CUSTOMER";
        this.password = password;
        this.phone = phone;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loginType=' CUSTOMER" + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
