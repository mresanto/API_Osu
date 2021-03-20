package com.example.osu.Classes;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UsuarioHttp {
    private static String readStream(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try{
            while((line = r.readLine()) !=null){
                total.append(line).append('\n');
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return total.toString();
    }
    private static String request(String stringUrl){
        URL url = null;
        HttpURLConnection urlConnection = null;
        try{
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.OpenConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            urlConnection.disconnect();
        }
        return "";
    }
    public static Usuario usuario(String username){
        String resposta = request("https://osu.ppy.sh/p/api/get_user?k=6e92e780d46658e3193244c48582b2f64e2e4538&m=0&u=" + nome);
        JSONObject obj = new JSONObject(resposta);
        String username = obj.getString("username");
        int id = obj.getInt("user_id");
        String pp_rank = obj.getString("pp_rank");
        int level = obj.getInt("level");
        int playcount = obj.getInt("playcount");
        String accuracy = obj.getString("accuracy");
        int count_rank_ss = obj.getInt("count_rank_ss");
        int count_rank_ssh = obj.getInt("count_rank_ssh");
        int count_rank_s = obj.getInt("count_rank_s");
        int count_rank_sh = obj.getInt("count_rank_sh");
        int count_rank_a = obj.getInt("count_rank_a");
        return new Usuario(username, id, pp_rank, level, playcount, accuracy, count_rank_ss, count_rank_ssh, count_rank_s, count_rank_sh, count_rank_sh);
    }
}
