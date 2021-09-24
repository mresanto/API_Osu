package com.example.osu.CarregarClasses;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.osu.Utilits.NetworkUtilsLogin;

public class CarregaLogin extends AsyncTaskLoader<String> {
    private String mQueryString;

    public CarregaLogin(Context context, String queryString){
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
        return NetworkUtilsLogin.buscaUsername(mQueryString);
    }
}
