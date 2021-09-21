package com.example.osu.ActionClasse;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.osu.CarregarClasses.CarregaScore;
import com.example.osu.Classes.UserRecent;
import com.example.osu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityScore extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText username;
    private TextView beatmap;
    private TextView TXTscore;
    private TextView maxcombo;
    private TextView countmiss;
    private TextView rank;
    private Button btnHist;
    private String copiaUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        username = findViewById(R.id.edPesquisaUsuario);
        beatmap = findViewById(R.id.beatmap);
        TXTscore = findViewById(R.id.score);
        maxcombo = findViewById(R.id.maxcombo);
        countmiss = findViewById(R.id.countmiss);
        rank = findViewById(R.id.rank);
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        btnHist = findViewById(R.id.btnHistSCore);
        btnHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityScore.this, HistoricoScore.class);
                startActivity(intent);
            }
        });


    }
    public void buscaUsuario(View view){
        String queryString = username.getText().toString();
        copiaUsername= username.getText().toString();
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
            username.setText(R.string.str_empty);
            beatmap.setText(R.string.loading);
        }
        else {
            if (queryString.length() == 0) {
                username.setText(R.string.str_empty);
                beatmap.setText(R.string.no_search_term);
            } else {
                username.setText(" ");
                username.setText(R.string.no_network);
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
        return new CarregaScore(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{
            JSONArray jsonArray = new JSONArray(data);
            UserRecent user = new UserRecent();

            JSONObject score = jsonArray.getJSONObject(0);
            try{
                user.setBeatmap((score.getString("beatmap_id")));
                user.setScore(score.getString("score"));
                user.setMaxcombo(score.getString("maxcombo"));
                user.setCountmiss(score.getString("countmiss"));
                user.setRank(score.getString("rank"));
                user.setUsername(copiaUsername);
            }catch(JSONException e){
                e.printStackTrace();
            }
            if(user.beatmap != null){
                beatmap.setText("Id do mapa: " + user.beatmap);
                TXTscore.setText("Score: " + user.score);
                maxcombo.setText("Combo: " + user.maxcombo);
                countmiss.setText("Misscount: " + user.countmiss);
                rank.setText("Rank: " + user.rank);

                try{
                    SalvarScore(user);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            } else{
                username.setHint("Usuário não econtrado");
            }
        } catch( Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
    public void SalvarScore(UserRecent score){
        ActionScore salvar = new ActionScore(this);
        salvar.AdicionarScore(score);
    }
}