package com.example.osu.Classes;

public class Login {

    public int UserId;
    public String Username;
    public String Email;
    public String Senha;

    public Login(int userId, String username, String email, String senha) {
        UserId = userId;
        Username = username;
        Email = email;
        Senha = senha;
    }

    public Login(){

    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }


}
