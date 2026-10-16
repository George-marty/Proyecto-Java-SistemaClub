package com.example.modelo;



public class Usuario {
    private int id;
    private String username;
    private String passwordHash;
    

    public Usuario() {
    }

    public Usuario(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        
    }

    public Usuario(int id, String username, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", username=" + username  + "]";
    }

}
