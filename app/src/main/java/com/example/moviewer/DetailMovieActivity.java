package com.example.moviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailMovieActivity extends AppCompatActivity {

    String title, rating, overview, path, published_date;

    ImageView detailImage;
    TextView judulFilm,ratingFilm,releaseDate,viewers, detailSynopsis;
    FloatingActionButton favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        title = getIntent().getStringExtra("title");
        rating = getIntent().getStringExtra("rating");
        overview = getIntent().getStringExtra("overview");
        path = getIntent().getStringExtra("moviePath");
        published_date = getIntent().getStringExtra("releaseDate");
       // Log.d("DetailMovie", title + rating + overview + path + published_date);

        detailImage = findViewById(R.id.imageViewDetailImage);
        judulFilm = findViewById(R.id.tvDetailJudul);
        ratingFilm = findViewById(R.id.tvDetailRating);
        releaseDate = findViewById(R.id.tvDetailReleaseDate);
        detailSynopsis = findViewById(R.id.tvDetailSinopsis);
        favorite = findViewById(R.id.buttonfavorite);

        judulFilm.setText(title);
        ratingFilm.setText(rating);
        releaseDate.setText(published_date);
        detailSynopsis.setText(overview);
        Glide.with(this).load(path).into(detailImage);
    }
}