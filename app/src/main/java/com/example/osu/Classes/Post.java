package com.example.osu.Classes;

import java.util.Date;

public class Post {

    public int PostId;
    public String Titulo;
    public String Sub_Titulo;
    public String Descricao;
    public String Visualizacoes;
    public String Date;
    public String Username;

    public Post(int postId, String titulo, String sub_Titulo, String descricao, String visualizacoes, String date, String username) {
        PostId = postId;
        Titulo = titulo;
        Sub_Titulo = sub_Titulo;
        Descricao = descricao;
        Visualizacoes = visualizacoes;
        Date = date;
        Username = username;
    }

    public Post() {
    }

    public int getPostId() {
        return PostId;
    }

    public void setPostId(int postId) {
        PostId = postId;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getSub_Titulo() {
        return Sub_Titulo;
    }

    public void setSub_Titulo(String sub_Titulo) {
        Sub_Titulo = sub_Titulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getVisualizacoes() {
        return Visualizacoes;
    }

    public void setVisualizacoes(String visualizacoes) {
        Visualizacoes = visualizacoes;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
