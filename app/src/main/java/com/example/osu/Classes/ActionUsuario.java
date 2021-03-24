package com.example.osu.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.osu.Utilits.DBHelper;

public class ActionUsuario extends DBHelper {


    public ActionUsuario(@Nullable Context context) {
        super(context);
    }

    public boolean AdicionarUsuario(Usuario usuario ){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Username", usuario.username);
        cv.put("PP_rank", usuario.pp_rank);
        cv.put("Level", usuario.level);
        cv.put("Playcount", usuario.playcount);
        cv.put("Accuracy", usuario.accuracy);
        cv.put("Rank_ss", usuario.count_rank_ss);
        cv.put("Rank_ssh", usuario.count_rank_ssh);
        cv.put("Ranks_s", usuario.count_rank_s);
        cv.put("Rank_sh", usuario.count_rank_sh);
        cv.put("Rank_a", usuario.count_rank_a);

        long result = db.insert("Usuario ",null,cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Usuario BuscarUsuario(Integer id_pesquisa){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Usuario WHERE User_id = " + id_pesquisa + " ;";
        Cursor c =db.rawQuery(sql, null);

        Usuario usuario = new Usuario();

        Integer id = c.getInt(c.getColumnIndex("User_id"));
        String usernmae = c.getString(c.getColumnIndex("Username"));
        String pp_rank = c.getString(c.getColumnIndex("PP_rank"));
        Double level = c.getDouble(c.getColumnIndex("Level"));
        Integer playcount = c.getInt(c.getColumnIndex("Playcount"));
        Double accuracy = c.getDouble(c.getColumnIndex("Accuracy"));
        Integer rank_ss = c.getInt(c.getColumnIndex("Rank_ss"));
        Integer rank_ssh = c.getInt(c.getColumnIndex("Rank_ssh"));
        Integer rank_s = c.getInt(c.getColumnIndex("Ranks_s"));
        Integer rank_sh = c.getInt(c.getColumnIndex("Rank_sh"));
        Integer rank_a = c.getInt(c.getColumnIndex("Rank_a"));


        usuario.setUserId(id);
        usuario.setUsername(usernmae);
        usuario.setPp_rank(pp_rank);
        usuario.setLevel(level);
        usuario.setAccuracy(accuracy);
        usuario.setCount_rank_ss(rank_ss);
        usuario.setCount_rank_sh(rank_sh);
        usuario.setCount_rank_s(rank_s);
        usuario.setCount_rank_ssh(rank_ssh);
        usuario.setCount_rank_a(rank_a);


        return usuario;
    }
}
