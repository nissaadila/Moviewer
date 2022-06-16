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

import java.util.Vector;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    Context ctx;
    Vector<Movie> movies;
    String url;

    public MovieAdapter(Context ctx){
        this.ctx = ctx;
    }

    public void setMovies(Vector<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        url = movies.get(position).getPath();
        Glide.with(ctx).load(url).into(holder.moviePic);
        holder.title.setText(""+movies.get(position).title);
        holder.rating.setText(""+movies.get(position).rating);
        holder.overview.setText(""+movies.get(position).overview);
        holder.releaseDate = movies.get(position).releaseDate;
        holder.moviePath = movies.get(position).path;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView movieView;
        ImageView moviePic;
        TextView title, rating, overview;
        String releaseDate, moviePath;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieView = itemView.findViewById(R.id.movieView);
            movieView.setOnClickListener(this);
            moviePic = itemView.findViewById(R.id.moviePicture);
            title = itemView.findViewById(R.id.movieName);
            rating = itemView.findViewById(R.id.movieRating);
            overview = itemView.findViewById(R.id.overview);
        }

        @Override
        public void onClick(View view) {
            //pindah ke detail movie
            Log.d("testClick", "hai");
            Intent i = new Intent(view.getContext(), DetailActivity.class);
            i.putExtra("title", title.getText().toString());
            i.putExtra("rating", rating.getText().toString());
            i.putExtra("overview", overview.getText().toString());
            i.putExtra("releaseDate", releaseDate);
            i.putExtra("moviePath", moviePath);

            view.getContext().startActivity(i);
        }
    }
}

