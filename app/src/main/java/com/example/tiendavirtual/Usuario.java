package com.example.tiendavirtual;

public class Usuario {
    private String name;
    private String email;
    private String password;

    public Usuario(String name, String email, String password ){
        this.name = name;
        this.password = password;
        this.email = email;
    }

    // Métodos getters
    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
