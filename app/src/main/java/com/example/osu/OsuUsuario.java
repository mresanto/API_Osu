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
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.osu.CarregarClasses.CarregaUsuario;
import com.example.osu.Classes.ActionUsuario;
import com.example.osu.Classes.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OsuUsuario extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText userName;
    private TextView TXTID;
    private TextView TXTusuario;
    private TextView TXTlevel;
    private TextView TXTplaycount;
    private TextView TXTaccuracy;
    private TextView TXTSS;
    private TextView TXTSSH;
    private TextView TXTS;
    private TextView TXTSH;
    private TextView TXTA;
    private TextView pp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osu_usuario);
        TXTID = findViewById(R.id.txtID);
        TXTusuario = findViewById(R.id.txtUsername);
        pp = findViewById(R.id.txtPP);
        TXTlevel = findViewById(R.id.txtLevel);
        TXTplaycount = findViewById(R.id.txtPlaycount);
        TXTaccuracy = findViewById(R.id.txtAcc);
        TXTSS = findViewById(R.id.txtSS);
        TXTSSH = findViewById(R.id.txtSSH);
        TXTS = findViewById(R.id.txtS);
        TXTSH = findViewById(R.id.txtSH);
        TXTA = findViewById(R.id.txtA);
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
        else {
            if (queryString.length() == 0) {
                userName.setText(R.string.str_empty);
                pp.setText(R.string.no_search_term);
            } else {
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
            Usuario usuario = new Usuario();
            usuario = null;

           // String id = null;
           // String username = null;
           // String ppRank = null;
           // String level = null;
           // String playcount = null;
           // String accuracy = null;
           // String SS = null;
           // String SSH = null;
           // String S = null;
           // String SH = null;
           // String A = null;

            JSONObject user = jsonArray.getJSONObject(0);
            try{
                usuario.setUserId(user.getInt("user_id"));
                usuario.setUsername(user.getString("username"));
                usuario.setPlaycount(user.getInt("playcount"));
                usuario.setPp_rank(user.getString("pp_rank"));
                usuario.setLevel( user.getDouble("level"));
                usuario.setAccuracy( user.getDouble("accuracy"));
                usuario.setCount_rank_ss( user.getInt("count_rank_ss"));
                usuario.setCount_rank_ssh(user.getInt("count_rank_ssh"));
                usuario.setCount_rank_s(user.getInt("count_rank_s"));
                usuario.setCount_rank_sh(user.getInt("count_rank_sh"));
                usuario.setCount_rank_a(user.getInt("count_rank_a"));
                //id = user.getString("user_id");
                //username = user.getString("username");
                //playcount = user.getString("playcount");
                //ppRank = user.getString("pp_rank");
                //level = user.getString("level");
                //accuracy = user.getString("accuracy");
                //SS = user.getString("count_rank_ss");
                //SSH = user.getString("count_rank_ssh");
                //S = user.getString("count_rank_s");
                //SH = user.getString("count_rank_sh");
                //A = user.getString("count_rank_a");
            }catch(JSONException e){
                e.printStackTrace();

        }
            if(usuario.pp_rank != null){
                TXTID.setText("ID: " + usuario.userId);
                TXTusuario.setText("Username: " + usuario.username);
                pp.setText("Rank: " + usuario.pp_rank);
                TXTlevel.setText("Level:" + usuario.level);
                TXTplaycount.setText("Playcount: " + usuario.playcount);
                TXTaccuracy.setText("Accuracy: " + usuario.accuracy);
                TXTSS.setText("SS: " + usuario.count_rank_ss);
                TXTSSH.setText("SS: " + usuario.count_rank_ssh);
                TXTS.setText("S: " + usuario.count_rank_s);
                TXTSH.setText("S: " + usuario.count_rank_sh);
                TXTA.setText("A: " + usuario.count_rank_a);

                try {
                    SalvarOsu(usuario);
                }
                catch (Exception e){
                  e.printStackTrace();
                }
            } else{
                userName.setHint("usuário não encontrado");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void SalvarOsu(Usuario usuario){
        ActionUsuario salvar = new ActionUsuario(this);
        salvar.AdicionarUsuario(usuario);
    }
}