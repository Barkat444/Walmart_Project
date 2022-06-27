package com.stackroute.wipro.backend.User.model;

public class JwtRequest {

    private  String username;
    private  String password;

    public JwtRequest() {
        super();
    }
    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {this.username = username;}

    public String getPassword(){return password;}

    public void setPassword(String password) {this.password = password;}

    @Override
    public String toString() {
        return "JwtRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {return username;}

}
