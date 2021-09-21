package com.example.osu.ActionClasse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.osu.ActionClasse.ActionScore;
import com.example.osu.R;

import java.util.ArrayList;

public class HistoricoScore extends AppCompatActivity {

    private ProgressBar pbHistScore;
    private int sizescore;
    private Handler mHandler = new Handler();
    private boolean run = true;

    private ListView lista;
    ArrayList<String> score;
    ArrayAdapter<String> listaadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_score);

        pbHistScore = (ProgressBar) findViewById(R.id.pbhistScore);
        lista = (ListView) findViewById(R.id.lvScores);
        lista.setVisibility(View.INVISIBLE);

        ActionScore scores = new ActionScore(this);
        long delay = 1000;

        score = new ArrayList(scores.ListarScore());
        sizescore = score.size();

        pbHistScore.setMax(sizescore-1);
        pbHistScore.setVisibility(View.VISIBLE);

        listaadd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    for (int i = 0; i < sizescore; i++) {
                        try {
                            pbHistScore.setProgress(i);
                            listaadd.add(score.get(i));
                            Thread.sleep(100);
                        }
                        catch (InterruptedException e){
                            System.out.println("Erro de carregamento");
                            Log.i("Slepp", "Erro de carregamento");
                            e.printStackTrace();
                        }
                    }
                    run = false;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            lista.setAdapter(listaadd);
                            lista.setVisibility(View.VISIBLE);
                            pbHistScore.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });
        t1.start();
        if(run == false){
            t1.interrupt();
        }

    }
}