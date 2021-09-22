package com.example.osu.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.osu.R;

public class DetalhePost extends AppCompatActivity {

    private TextView Date;
    private TextView Autor;
    private TextView Desc;
    private int postid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_post);

        Autor = (TextView) findViewById(R.id.txtAutor);
        Date = (TextView) findViewById(R.id.txtData);
        Desc = (TextView) findViewById(R.id.txtDescricao);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Autor.setText(bundle.getString("titulo"));
        }
    }
}