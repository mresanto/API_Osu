package com.example.osu.Classes;

public class UserRecent {

    public String getBeatmap(){return beatmap; }
    public void setBeatmap(String beatmap_id){this.beatmap = beatmap; }

    public String getScore(){return score; }
    public void setScore(String score){this.score = this.score; }

    public String getMaxcombo(){return maxcombo; }
    public void setMaxcombo(String maxcombo){this.maxcombo = this.maxcombo; }

    public String getCountmiss(){return countmiss; }
    public void setCountmiss(String countmiss){this.countmiss = this.countmiss;}

    public String getRank(){return rank; }
    public void setRank(String rank){this.rank = this.rank; }

    public String beatmap;
    public String score;
    public String maxcombo;
    public String countmiss;
    public String rank;
}
