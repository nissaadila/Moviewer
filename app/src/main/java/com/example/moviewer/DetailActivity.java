package com.example.moviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviewer.Database.DatabaseHelper;
import com.example.moviewer.Database.FavouriteHelper;
import com.example.moviewer.Database.UserHelper;
import com.example.moviewer.Fragments.FavouriteFragment;
import com.example.moviewer.Models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    String title, rating, overview, path, published_date;

    ImageView detailImage;
    TextView judulFilm,ratingFilm,releaseDate, detailSynopsis;
    FloatingActionButton favoriteButton;
    FavouriteHelper favouriteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        init();
        favoriteButton.setOnClickListener(this);

    }

    public void init(){
        title = getIntent().getStringExtra("title");
        rating = getIntent().getStringExtra("rating");
        overview = getIntent().getStringExtra("overview");
        path = getIntent().getStringExtra("moviePath");
        published_date = getIntent().getStringExtra("releaseDate");


        detailImage = findViewById(R.id.imageViewDetailImage);
        judulFilm = findViewById(R.id.tvDetailJudul);
        ratingFilm = findViewById(R.id.tvDetailRating);
        releaseDate = findViewById(R.id.tvDetailReleaseDate);
        detailSynopsis = findViewById(R.id.tvDetailSinopsis);
        favoriteButton = findViewById(R.id.buttonfavorite);

        judulFilm.setText(title);
        ratingFilm.setText(rating);
        releaseDate.setText(published_date);
        detailSynopsis.setText(overview);
        Glide.with(this).load(path).into(detailImage);
    }


    @Override
    public void onClick(View button) {
        if (button.getId() == R.id.buttonfavorite){
            Movie movie = new Movie(title,overview,path,rating,published_date);
            favouriteHelper.insertFavourite(movie);
            Intent moveFavorite = new Intent(DetailActivity.this,MainActivity.class);
            startActivity(moveFavorite);
            Toast.makeText(this,"Success Add Favorite", Toast.LENGTH_SHORT).show();
            finish();

        }
    }
}