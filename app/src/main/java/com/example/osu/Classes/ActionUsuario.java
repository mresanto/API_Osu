package com.example.osu.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.osu.Utilits.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ActionUsuario extends DBHelper {


    public ActionUsuario(@Nullable Context context) {
        super(context);
    }

    public boolean AdicionarUsuario(Usuario usuario ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("User_id", usuario.userId);
        cv.put("Username", usuario.username);
        cv.put("PP_rank", usuario.pp_rank);
        cv.put("Level", usuario.level);
        cv.put("Playcount", usuario.playcount);
        cv.put("Accuracy", usuario.accuracy);
        cv.put("Rank_ss", usuario.count_rank_ss);
        cv.put("Rank_ssh", usuario.count_rank_ssh);
        cv.put("Rank_s", usuario.count_rank_s);
        cv.put("Rank_sh", usuario.count_rank_sh);
        cv.put("Rank_a", usuario.count_rank_a);

        long result = db.insert("Usuario ",null,cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public List<String> listarUsuario(){
        SQLiteDatabase db = getReadableDatabase();
        List<String> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario ;";
        Cursor c =db.rawQuery(sql, null);

        while(c.moveToNext()) {

            //Usuario usuario = new Usuario();
            //String id = c.getString(c.getColumnIndex("User_id"));
            String username = c.getString(c.getColumnIndex("Username"));
            //String pp_rank = c.getString(c.getColumnIndex("PP_rank"));
            //String level = c.getString(c.getColumnIndex("Level"));
            //String playcount = c.getString(c.getColumnIndex("Playcount"));
            //String accuracy = c.getString(c.getColumnIndex("Accuracy"));
            //String rank_ss = c.getString(c.getColumnIndex("Rank_ss"));
            //String rank_ssh = c.getString(c.getColumnIndex("Rank_ssh"));
            //String rank_s = c.getString(c.getColumnIndex("Ranks_s"));
            //String rank_sh = c.getString(c.getColumnIndex("Rank_sh"));
            //String rank_a = c.getString(c.getColumnIndex("Rank_a"));
//
//
            //usuario.setUserId(id);
            //usuario.setUsername(usernmae);
            //usuario.setPp_rank(pp_rank);
            //usuario.setLevel(level);
            //usuario.setPlaycount(playcount);
            //usuario.setAccuracy(accuracy);
            //usuario.setCount_rank_ss(rank_ss);
            //usuario.setCount_rank_sh(rank_sh);
            //usuario.setCount_rank_s(rank_s);
            //usuario.setCount_rank'_ssh(rank_ssh);
            //usuario.setCount_rank_a(rank_a);
//
            usuarios.add(username);
        }
        return usuarios;
    }
}
