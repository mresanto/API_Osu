package com.example.osu.Classes;

import java.util.Date;

public class Comment {

    public int CommentId;
    public String Titulo;
    public String Descricoes;
    public Date Date;

    public Comment(int commentId, String titulo, String descricoes, java.util.Date date) {
        CommentId = commentId;
        Titulo = titulo;
        Descricoes = descricoes;
        Date = date;
    }

    public int getCommentId() {
        return CommentId;
    }

    public void setCommentId(int commentId) {
        CommentId = commentId;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescricoes() {
        return Descricoes;
    }

    public void setDescricoes(String descricoes) {
        Descricoes = descricoes;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }


}
