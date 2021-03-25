package com.example.osu.Classes;

import android.widget.ListAdapter;

public class Usuario {

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPp_rank() {
        return pp_rank;
    }

    public void setPp_rank(String pp_rank) {
        this.pp_rank = pp_rank;
    }

    public String getLevel(){ return level;}

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getCount_rank_ss() {
        return count_rank_ss;
    }

    public void setCount_rank_ss(String count_rank_ss) {
        this.count_rank_ss = count_rank_ss;
    }

    public String getCount_rank_ssh() {
        return count_rank_ssh;
    }

    public void setCount_rank_ssh(String count_rank_ssh) {
        this.count_rank_ssh = count_rank_ssh;
    }

    public String getCount_rank_s() {
        return count_rank_s;
    }

    public void setCount_rank_s(String count_rank_s) {
        this.count_rank_s = count_rank_s;
    }

    public String getCount_rank_sh() {
        return count_rank_sh;
    }

    public void setCount_rank_sh(String count_rank_sh) {
        this.count_rank_sh = count_rank_sh;
    }

    public String getCount_rank_a() {
        return count_rank_a;
    }

    public void setCount_rank_a(String count_rank_a) {
        this.count_rank_a = count_rank_a;
    }

    public int userId;
    public String username;
    public String pp_rank;
    public String level;
    public String playcount;
    public String accuracy;
    public String count_rank_ss;
    public String count_rank_ssh;
    public String count_rank_s;
    public String count_rank_sh;
    public String count_rank_a;

}
