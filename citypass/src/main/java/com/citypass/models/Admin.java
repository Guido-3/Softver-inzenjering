package com.citypass.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Admin {
    @JsonProperty("username")
    private String username;
    @JsonProperty("sifra")
    private String password;

    // Getters and setters
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

    // Constructors
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }
}
