package com.example.osu.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.osu.R;

public class Login extends AppCompatActivity {

    EditText editTextUser, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUser = (EditText)findViewById(R.id.editTextUsuario);
        editTextSenha = (EditText)findViewById(R.id.editTextSenha);
    }
    public void Entrar(View view) {
        if(validacao()) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra("Valor", editTextUser.getText().toString());
            startActivity(intent);
        }
    }
    public boolean validacao(){

        boolean retorno=true;

        String User = editTextUser.getText().toString();
        String Senha = editTextSenha.getText().toString();
        if(User.isEmpty() || Senha.isEmpty()){
            retorno = false;
            editTextUser.setError("Preencha este campo!");
            editTextSenha.setError("Preencha este campo!");
        }
        return retorno;
    }
}