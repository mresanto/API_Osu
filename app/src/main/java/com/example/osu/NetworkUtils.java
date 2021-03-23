package com.example.osu;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String OSU_URL = "https://osu.ppy.sh/api/get_user?";
    private static final String TOKEN = "6e92e780d46658e3193244c48582b2f64e2e4538";
    private static final String QUERY_PARAM_KEY = "k";
    private static final String QUERY_PARAM = "u";
    private static final String QUERY_PARAM_MODE = "m";
    private static final String MODE = "0";


    public static String buscaUsername(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String userJSONString = null;
        try {
            Uri builtURI = Uri.parse(OSU_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM_KEY, TOKEN)
                    .appendQueryParameter(QUERY_PARAM_MODE, MODE)
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .build();
            URL requestURL = new URL(builtURI.toString());

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
