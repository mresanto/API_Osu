package com.example.osu.ActionClasse;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.osu.Classes.Post;
import com.example.osu.Classes.UserRecent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActionPost {

    public boolean AdicionarPost(Post post ){
       return true;
    }

    public ArrayList<Post> ListarPost(){
        ArrayList<Post> posts = new ArrayList<>();
        int j= 1;
        for (int i = 0; i<2; i++) {

            Post post = new Post();
            post.setTitulo("Titulo");
            post.setSub_Titulo("Sub");
            post.setDescricao("Desc");
            post.setVisualizacoes("2");
            post.setDate("22/09/2021");
            post.setUsername("Username");
//
            posts.add(post);
        }
        return posts;
    }

}
