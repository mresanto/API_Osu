package com.example.osu.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.osu.R;

public class MainActivity extends AppCompatActivity {

    Button btnUsuario;
    Button btnScore;
    Button btnLocalizacao;
    Button btnForum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUsuario = findViewById(R.id.btnUsuario);
        btnScore = findViewById(R.id.btnScore);
        btnLocalizacao = findViewById(R.id.btnLoc);
        btnForum = findViewById(R.id.btnForum);


        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OsuUsuario.class);
                startActivity(intent);
            }
        });

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityScore.class);
                startActivity(intent);
            }
        });

        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Localizacao.class);
                startActivity(intent);
            }
        });

        btnForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityPost.class);
                startActivity(intent);
            }
        });





    }
}