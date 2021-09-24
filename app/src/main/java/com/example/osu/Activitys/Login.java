package com.example.osu.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.osu.Adapter.AdapterPost;
import com.example.osu.CarregarClasses.CarregaPost;
import com.example.osu.Classes.Post;
import com.example.osu.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    EditText editTextUser, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUser = (EditText)findViewById(R.id.editTextUsuario);
        editTextSenha = (EditText)findViewById(R.id.editTextSenha);


    }
    public void Entrar(View view) {
        if(validacao()) {
            if (getSupportLoaderManager().getLoader(0) != null) {
                getSupportLoaderManager().initLoader(0, null, this);
            }

            String User = editTextUser.getText().toString();
            String Senha = editTextSenha.getText().toString();

            String queryString = User + ";" + Senha + ";";


            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;


            if (connMgr != null) {
                networkInfo = connMgr.getActiveNetworkInfo();
            }

            if (networkInfo != null && networkInfo.isConnected()
                    && queryString.length() != 0) {
                Bundle queryBundle = new Bundle();
                queryBundle.putString("queryString", queryString);
                getSupportLoaderManager().restartLoader(0, queryBundle, this);
            }


        }
    }
    public boolean validacao(){

        boolean retorno=true;

        String User = editTextUser.getText().toString();
        String Senha = editTextSenha.getText().toString();
        if(User.isEmpty() || Senha.isEmpty()){
            retorno = false;
            editTextUser.setError("Preencha este campo!");
            editTextSenha.setError("Preencha este campo!");
        }
        return retorno;
    }

    @NonNull
    @NotNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {

        String User = editTextUser.getText().toString();
        String Senha = editTextSenha.getText().toString();
        String queryString = User + ";" + Senha + ";";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new CarregaPost(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull @NotNull Loader<String> loader, String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            com.example.osu.Classes.Login login1 = new com.example.osu.Classes.Login();

            try {
                    JSONObject loginapi = jsonArray.getJSONObject(0);
                    login1.setUserId(loginapi.getInt("id"));
                    login1.setUsername(loginapi.getString("username"));
                    login1.setSenha(loginapi.getString("senha"));
                    login1.setEmail(loginapi.getString("email"));

                String User = editTextUser.getText().toString();
                String Senha = editTextSenha.getText().toString();

                if(login1.Username == User && login1.Senha == Senha) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("Valor", editTextUser.getText().toString());
                    startActivity(intent);
                }


            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        catch (Exception e){

        }
    }

    @Override
    public void onLoaderReset(@NonNull @NotNull Loader<String> loader) {

    }
}