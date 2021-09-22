package com.example.osu.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.osu.ActionClasse.ActionUsuario;
import com.example.osu.R;

import java.util.ArrayList;

public class HistoricoUsuario extends AppCompatActivity {

    private ProgressBar pbHist;
    private int sizeusuario;
    private Handler mHandler = new Handler();
    private boolean run = true;

    private ListView lista;
    ArrayList<String> cliente;
    ArrayAdapter<String> listaadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_usuario);

        pbHist = (ProgressBar) findViewById(R.id.pbhist);
        lista = (ListView) findViewById(R.id.lvUsuarios);
        lista.setVisibility(View.INVISIBLE);

        ActionUsuario usuario = new ActionUsuario(this);
        long delay = 1000;

        cliente = new ArrayList(usuario.listarUsuario());
        sizeusuario = cliente.size();

        pbHist.setMax(sizeusuario-1);
        pbHist.setVisibility(View.VISIBLE);

        listaadd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    for (int i = 0; i < sizeusuario; i++) {
                        try {
                            pbHist.setProgress(i);
                            listaadd.add(cliente.get(i));
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
                            pbHist.setVisibility(View.INVISIBLE);
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