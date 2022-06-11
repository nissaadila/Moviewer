package com.example.moviewer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviewer.Database.FavouriteHelper;

import java.util.ArrayList;
import java.util.Vector;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>{
    private Context ctx;
    private ArrayList ALtitle, ALoverview, ALpath, ALrating;

    public FavouriteAdapter(Context ctx, ArrayList title, ArrayList overview, ArrayList path, ArrayList rating){
        this.ctx = ctx;
        ALtitle = title;
        ALoverview = overview;
        ALpath = path;
        ALrating = rating;
    }

    @NonNull
    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.MyViewHolder holder, int position) {
        String url = String.valueOf(ALpath.get(position));
        Log.d("RepeatPic", url);
        Glide.with(ctx).load(url).into(holder.moviePic);
        holder.title.setText(String.valueOf(ALtitle.get(position)));
        holder.rating.setText(String.valueOf(ALrating.get(position)));
        holder.overview.setText(String.valueOf(ALoverview.get(position)));
    }

    @Override
    public int getItemCount() {
        return ALoverview.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView movieFavourite;
        ImageView moviePic;
        TextView title, rating, overview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            movieFavourite = itemView.findViewById(R.id.movieFavourite);
            movieFavourite.setOnClickListener(this);
            moviePic = itemView.findViewById(R.id.moviePicture);
            title = itemView.findViewById(R.id.movieName);
            rating = itemView.findViewById(R.id.movieRating);
            overview = itemView.findViewById(R.id.overview);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
