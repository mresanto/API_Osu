package com.example.osu.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.osu.Utilits.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ActionScore extends DBHelper {

    public ActionScore(@Nullable Context context) {
        super(context);
    }

    public boolean AdicionarScore(UserRecent score ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("beatmap", score.beatmap);
        cv.put("score", score.score);
        cv.put("maxcombo", score.maxcombo);
        cv.put("countmiss", score.countmiss);
        cv.put("rank", score.rank);

        long result = db.insert("Score ",null,cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public List<UserRecent> ListarScore(){
        SQLiteDatabase db = getReadableDatabase();
        List<UserRecent> scores = new ArrayList<>();
        String sql = "SELECT * FROM Score ;";
        Cursor c =db.rawQuery(sql, null);

        while(c.moveToNext()) {

            UserRecent score = new UserRecent();
            score.setBeatmap(c.getString(c.getColumnIndex("beatmap")));
            score.setScore(c.getString(c.getColumnIndex("score")));
//
            scores.add(score);
        }
        return scores;
    }
}
