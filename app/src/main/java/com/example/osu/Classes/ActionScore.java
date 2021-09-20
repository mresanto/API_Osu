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
        cv.put("beatmap_id", score.beatmap);
        cv.put("score", score.score);
        cv.put("maxcombo", score.maxcombo);
        cv.put("countmiss", score.countmiss);
        cv.put("rank", score.rank);
        cv.put("username", score.username);

        long result = db.insert("Score ",null,cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public List<String> ListarScore(){
        SQLiteDatabase db = getReadableDatabase();
        List<String> scores = new ArrayList<>();
        String sql = "SELECT * FROM Score ;";
        Cursor c =db.rawQuery(sql, null);

        while(c.moveToNext()) {

            UserRecent score = new UserRecent();
            score.setBeatmap(c.getString(c.getColumnIndex("beatmap_id")));
            score.setScore(c.getString(c.getColumnIndex("score")));
            score.setUsername(c.getString(c.getColumnIndex("username")));
//
            scores.add("Username: "+ score.getUsername() + " BeatMap id: " + score.getBeatmap()+ " Score: " + score.getScore());
        }
        return scores;
    }
}
