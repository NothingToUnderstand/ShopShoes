package com.viktor.task.shoes.manager.web.dto;

public final class AuthRequest {
    private final String username;
    private final String password;

    public String getUsername() {
        return this.username;
    }


    public String getPassword() {
        return this.password;
    }


    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
}
