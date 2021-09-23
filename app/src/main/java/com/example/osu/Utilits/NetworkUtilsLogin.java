package com.example.osu.Utilits;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtilsLogin {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String URL = "http://192.168.0.102:5000/api/login/";
    private static final String QUERY_PARAM = "0";


    public static String buscaUsername(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String userJSONString = null;
        try {
            URL requestURL = new URL(URL + queryString);

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                builder.append(linha);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            userJSONString = builder.toString();
        } catch(IOException e){
            e.printStackTrace();
        }finally{
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try{
                    reader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, userJSONString);
        return userJSONString;
    }
}