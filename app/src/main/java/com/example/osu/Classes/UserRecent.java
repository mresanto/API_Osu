package com.example.osu.Classes;

public class UserRecent {

    public String getBeatmap() {
        return beatmap;
    }

    public void setBeatmap(String beatmap) {
        this.beatmap = beatmap;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMaxcombo() {
        return maxcombo;
    }

    public void setMaxcombo(String maxcombo) {
        this.maxcombo = maxcombo;
    }

    public String getCountmiss() {
        return countmiss;
    }

    public void setCountmiss(String countmiss) {
        this.countmiss = countmiss;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String beatmap;
    public String score;
    public String maxcombo;
    public String countmiss;
    public String rank;
    public String username;

    public UserRecent() {
        this.beatmap = beatmap;
        this.score = score;
        this.maxcombo = maxcombo;
        this.countmiss = countmiss;
        this.rank = rank;
        this.username = username;
    }
}
