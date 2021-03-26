package com.example.osu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.osu.Classes.ActionUsuario;
import com.example.osu.Classes.Usuario;

import java.util.ArrayList;
import java.util.List;

public class HistoricoUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_usuario);
        ActionUsuario usuario = new ActionUsuario(this);
        int position = 0;

        ListView lista = (ListView) findViewById(R.id.lvUsuarios);

        ArrayList<String> cliente = new ArrayList(usuario.listarUsuario());
        
        ArrayAdapter<String> listaadd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cliente);

        lista.setAdapter(listaadd);
    }
}