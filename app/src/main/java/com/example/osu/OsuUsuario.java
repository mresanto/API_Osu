package com.example.osu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.osu.CarregarClasses.CarregaUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.*;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class OsuUsuario extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText userName;
    private TextView pp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osu_usuario);
        pp = findViewById(R.id.textView2);
        userName = findViewById(R.id.edPesquisaUsuario);
        if (getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void buscaUsuario(View view){
        String queryString = userName.getText().toString();
        InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputManager != null){
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0){
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            userName.setText(R.string.str_empty);
            pp.setText(R.string.loading);
        }
        else{
            if(queryString.length() == 0){
                userName.setText(R.string.str_empty);
                pp.setText(R.string.no_search_term);
            }else{
                userName.setText(" ");
                pp.setText(R.string.no_network);
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if(args != null){
            queryString = args.getString("queryString");
        }
        return new CarregaUsuario(this, queryString);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{
            JSONArray jsonArray = new JSONArray(data);

            String ppRank = null;

            JSONObject user = jsonArray.getJSONObject(0);
            try{
                ppRank = user.getString("pp_rank");
            }catch(JSONException e){
                e.printStackTrace();

        }
            if(ppRank != null){
                pp.setText(ppRank);
            } else{
                pp.setText(R.string.str_empty);
            }
        } catch (Exception e){
            pp.setText("bostil");
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}