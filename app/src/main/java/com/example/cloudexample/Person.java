package com.example.cloudexample;

public class Person {

    private String username;
    private String password;
    private String mobile_number;

    public Person(String username, String password, String mobile_number) {
        this.username = username;
        this.password = password;
        this.mobile_number = mobile_number;
    }

    public Person() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
}
