package com.example.osu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.osu.Classes.ActionUsuario;
import com.example.osu.Classes.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

        pbHist.setMax(sizeusuario);
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