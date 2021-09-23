package com.example.osu.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.osu.ActionClasse.ActionPost;
import com.example.osu.Adapter.AdapterPost;
import com.example.osu.CarregarClasses.CarregaPost;
import com.example.osu.CarregarClasses.CarregaUsuario;
import com.example.osu.Classes.Post;
import com.example.osu.Classes.Usuario;
import com.example.osu.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import static androidx.core.content.ContextCompat.getSystemService;

public class ActivityPost extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private ActionPost actionpost;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }


        String queryString = "0";


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;


        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }


        lista = (ListView) findViewById(R.id.lvPost);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityPost.this, DetalhePost.class);

                Post post = (Post) lista.getItemAtPosition(position);

                intent.putExtra("titulo", post.getTitulo());
                startActivity(intent);
            }
        });
    }

    private ArrayList<Post> listarPost() {

        ArrayList<Post> posts = new ArrayList<Post>();
        Post e = new Post(1, "Titulo2", "Sub", "Descricao", "2", Calendar.getInstance().getTime().toString(), "Username");
        posts.add(e);
        e = new Post(2, "Titulo3", "Sub", "Descricao", "2", Calendar.getInstance().getTime().toString(), "Username");
        posts.add(e);
        return posts;
    }

    @NonNull
    @NotNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {
        String queryString = "";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new CarregaPost(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull @NotNull Loader<String> loader, String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            Post post = new Post();

            JSONObject postapi = jsonArray.getJSONObject(0);
            try {
                ArrayList<Post> posts = new ArrayList<Post>();

                post.setPostId((postapi.getInt("post_id")));
                post.setTitulo((postapi.getString("titulo")));
                post.setSub_Titulo((postapi.getString("sub_titulo")));
                post.setDescricao((postapi.getString("descricao")));
                post.setVisualizacoes((postapi.getString("visualizacoes")));
                post.setDate((postapi.getString("data_post")));
                post.setUsername((postapi.getString("user_id")));

                posts.add(post);


                lista = (ListView) findViewById(R.id.lvPost);


                ArrayAdapter adapter = new AdapterPost(this, posts);
                lista.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        catch (Exception e){

        }
    }

    @Override
    public void onLoaderReset(@NonNull @NotNull Loader<String> loader) {

    }
}