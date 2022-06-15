package com.example.moviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviewer.Database.DatabaseHelper;
import com.example.moviewer.Database.FavouriteHelper;
import com.example.moviewer.Database.UserHelper;
import com.example.moviewer.Models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView detailImage;
    TextView judulFilm,ratingFilm,releaseDate,viewers, detailSynopsis;
    FloatingActionButton favorite;
    FavouriteHelper favouriteHelper;
    SharedPreferences sharedPreferences;
    long movie_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        favorite.setOnClickListener(this);
    }

    @SuppressLint("ResourceType")
    private void init(){
        Intent intent = this.getIntent();
        favouriteHelper = new FavouriteHelper(this);
        detailImage = findViewById(R.id.imageViewDetailImage);
        judulFilm = findViewById(R.id.tvDetailJudul);
        ratingFilm = findViewById(R.id.tvDetailRating);
        releaseDate = findViewById(R.id.tvDetailReleaseDate);
        viewers = findViewById(R.id.tvDetailViewers);
        detailSynopsis = findViewById(R.id.tvDetailSinopsis);
        favorite = findViewById(R.id.buttonfavorite);

        if(intent != null){
            movie_id = intent.getLongExtra("position",0);
//            Cursor cursor = favouriteHelper.getFavouriterData(movie_id);
//            cursor.moveToFirst();
//
//            judulFilm.setText(cursor.getString(1));
//            detailSynopsis.setText(cursor.getString(2));
//
//            ratingFilm.setText(cursor.getString(4));
//            releaseDate.setText(cursor.getString(5));
//
//            cursor.close();
//            sharedPreferences = getSharedPreferences("LOG_IN",MODE_PRIVATE);




        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonfavorite){
            insertDataFavorite();
            Toast.makeText(this,"Success add favorite",Toast.LENGTH_LONG).show();
        }
    }

    private void insertDataFavorite(){
        String judul = judulFilm.getText().toString();
        String deskripsi = detailSynopsis.getText().toString();
        //image ga tau gimana get nya :)
        String rating = ratingFilm.getText().toString();
        String tanggalRealease = releaseDate.getText().toString();

        // Movie movie = new Movie(judul,deskripsi,,rating,tanggalRealease);
        //favouriteHelper.insertFavourite(movie);
    }
}