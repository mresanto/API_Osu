package com.example.osu.ActionClasse;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.osu.Classes.Post;
import com.example.osu.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

public class ActionPost implements LoaderManager.LoaderCallbacks<String> {

    public boolean AdicionarPost(Post post ){
       return true;
    }


    public ArrayList<Post> ListarPost() {

        String queryString = "0";

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

    public void buscaUsuario(View view) {


    }

    @NonNull
    @NotNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {

        return null;
    }

    @Override
    public void onLoadFinished(@NonNull @NotNull Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(@NonNull @NotNull Loader<String> loader) {

    }
}
