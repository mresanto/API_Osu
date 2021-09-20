package com.example.osu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.osu.Classes.ActionScore;
import com.example.osu.Classes.ActionUsuario;
import com.example.osu.Classes.UserRecent;

import java.util.ArrayList;

public class HistoricoScore extends AppCompatActivity {

    private ListView lista;
    ArrayList<String> score;
    ArrayAdapter<String> listaadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_score);

        lista = (ListView) findViewById(R.id.lvScores);
        ActionScore scores = new ActionScore(this);

        score = new ArrayList(scores.ListarScore());
        listaadd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,score);
        lista.setAdapter(listaadd);

    }
}