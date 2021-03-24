package com.example.osu.Utilits;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.osu.Classes.Usuario;

public class DBHelper extends SQLiteOpenHelper {

    private static int versao = 1;
    private static String nome = "Osu_DB";

    public DBHelper(@Nullable Context context){
        super(context,nome,null,versao);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String str= "CREATE TABLE Usuario(User_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Username VARCHAR(50), PP_rank INTEGER,Level REAL, Playcount INTEGER, Accuracy REAL, Rank_ss INTEGER, Rank_ssh INTEGER, Rank_s INTEGER, rank_sh INTEGER, rank_a INTEGER);";
        try {
            db.execSQL(str);
            Log.i("INFO DB", "Sucesso ao criar a tabela");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuario;");
        onCreate(db);
    }
}
