package com.example.osu.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.osu.ActionClasse.ActionPost;
import com.example.osu.Adapter.AdapterPost;
import com.example.osu.Classes.Post;
import com.example.osu.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityPost extends AppCompatActivity {

    private ActionPost actionpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final ListView lista = (ListView) findViewById(R.id.lvPost);
        ArrayList<Post> posts = listarPost();
        ArrayAdapter adapter = new AdapterPost(this, posts);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityPost.this, DetalhePost.class);

                Post post = (Post)lista.getItemAtPosition(position);

                intent.putExtra("titulo", post.getTitulo());
                startActivity(intent);
            }
        });
    }

    private ArrayList<Post> listarPost(){
        ArrayList<Post> posts = new ArrayList<Post>();
        Post e = new Post(1,"Titulo2","Sub","Descricao","2", Calendar.getInstance().getTime().toString(),"Username");
        posts.add(e);
        e = new Post(2,"Titulo3","Sub","Descricao","2", Calendar.getInstance().getTime().toString(),"Username");
        posts.add(e);
        return posts;
    }
}