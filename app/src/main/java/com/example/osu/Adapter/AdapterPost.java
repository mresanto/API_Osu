package com.example.osu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.osu.Classes.Post;
import com.example.osu.R;

import java.util.ArrayList;

public class AdapterPost extends ArrayAdapter<Post> {

    private final Context context;
    private final ArrayList<Post> posts;

    public AdapterPost(Context context, ArrayList<Post> posts){
        super(context, R.layout.adapterpost, posts);
        this.context = context;
        this.posts = posts;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapterpost, parent, false);

        TextView Titulo = (TextView) rowView.findViewById(R.id.txtTitulo);
        TextView Vis = (TextView) rowView.findViewById(R.id.txtVis);
        TextView Username = (TextView) rowView.findViewById(R.id.txtUser);
        TextView Data = (TextView) rowView.findViewById(R.id.txtDate);

        Titulo.setText(posts.get(position).getTitulo());
        Vis.setText(posts.get(position).getVisualizacoes());
        Username.setText(posts.get(position).getUsername());
        Data.setText(posts.get(position).getDate());

        return rowView;
    }
}
