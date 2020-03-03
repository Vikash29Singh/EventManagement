package com.example.eventmanager;

public class User {
    public String email, contact, organization , password;

    public User(){

    }

    public User(String email, String contact, String organization) {
        this.email = email;
        this.contact = contact;
        this.organization = organization;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
