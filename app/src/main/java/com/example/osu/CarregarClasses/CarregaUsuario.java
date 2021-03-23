package com.example.osu.CarregarClasses;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.osu.NetworkUtils;

public class CarregaUsuario extends AsyncTaskLoader<String> {
    private String mQueryString;
    public CarregaUsuario(Context context, String queryString){
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
        return NetworkUtils.buscaUsername(mQueryString);
    }
}
