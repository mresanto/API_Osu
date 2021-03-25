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
        String str= "CREATE TABLE Usuario(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, User_id TEXT, Username TEXT, PP_rank TEXT,Level TEXT, Playcount TEXT, Accuracy TEXT, Rank_ss TEXT, Rank_ssh TEXT, Rank_s TEXT, rank_sh TEXT, rank_a TEXT);";
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
