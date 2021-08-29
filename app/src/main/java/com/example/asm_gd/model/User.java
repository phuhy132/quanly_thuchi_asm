package com.example.asm_gd.model;

public class User {
    private  int maUser;
    private String Email;
    private String Password;

    public User(int maUser, String email, String password) {
        this.maUser = maUser;
        Email = email;
        Password = password;
    }
    public User( String email, String password) {
        Email = email;
        Password = password;
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
