package com.example.osu.CarregarClasses;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.osu.Utilits.NetworkUtilsLogin;
import com.example.osu.Utilits.NetworkUtilsPost;

public class CarregaPost extends AsyncTaskLoader<String> {
    private String mQueryString;

    public CarregaPost(Context context, String queryString){
        super(context);
        mQueryString = queryString;
    }
    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground(){
        return NetworkUtilsPost.buscaUsername();
    }
}
