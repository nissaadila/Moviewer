package com.example.moviewer;

import android.content.Context;
import android.content.Intent;
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
    private Vector<Movie> movies;
    String url;

    public FavouriteAdapter(Context ctx){
        this.ctx = ctx;
    }

    public void setMovies(Vector<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.movie_favorite, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.MyViewHolder holder, int position) {
        url = movies.get(position).getPath();
        Glide.with(ctx).load(url).into(holder.moviePic);
        holder.title.setText(""+movies.get(position).title);
        holder.rating.setText(""+movies.get(position).rating);
        holder.overview.setText(""+movies.get(position).overview);
        holder.releaseDate = movies.get(position).releaseDate;
        holder.moviePath = movies.get(position).path;
        holder.curr_id = movies.get(position).id;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView movieFavourite;
        ImageView moviePic;
        TextView title, rating, overview;
        String releaseDate, moviePath;
        int curr_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            movieFavourite = itemView.findViewById(R.id.movieFavourite);
            moviePic = itemView.findViewById(R.id.moviePicture);
            title = itemView.findViewById(R.id.movieName);
            rating = itemView.findViewById(R.id.movieRating);
            overview = itemView.findViewById(R.id.overview);
            movieFavourite.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("testClick", "hai");
            Intent i = new Intent(view.getContext(), DetailActivity.class);
            i.putExtra("title", title.getText().toString());
            i.putExtra("rating", rating.getText().toString());
            i.putExtra("overview", overview.getText().toString());
            i.putExtra("releaseDate", releaseDate);
            i.putExtra("moviePath", moviePath);
            i.putExtra("userID", curr_id);

            view.getContext().startActivity(i);
        }
    }

}
