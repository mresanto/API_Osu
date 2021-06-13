package com.example.osu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.osu.Classes.ActionUsuario;
import com.example.osu.Classes.Usuario;

import java.util.ArrayList;
import java.util.List;

public class HistoricoUsuario extends AppCompatActivity {

    private ProgressBar pbHist;
    private int pbHistStatus = 0;

    private Handler mHandler = new Handler();

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pbHistStatus < 100){
                    pbHistStatus++;
                    android.os.SystemClock.sleep(50);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            pbHist.setProgress(pbHistStatus);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista.setVisibility(View.VISIBLE);
                        pbHist.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();

        ActionUsuario usuario = new ActionUsuario(this);
        int position = 0;


        cliente = new ArrayList(usuario.listarUsuario());
        
        listaadd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cliente);

        lista.setAdapter(listaadd);
    }
}