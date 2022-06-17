package com.example.moviewer.Fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviewer.Database.DatabaseHelper;
import com.example.moviewer.Database.FavouriteHelper;
import com.example.moviewer.FavouriteAdapter;
import com.example.moviewer.R;

import java.util.ArrayList;
import java.util.Vector;


public class FavouriteFragment extends Fragment{

    TextView error;
    RecyclerView rv;

    FavouriteHelper db;
    Vector<String> title, overview, path, rating, releaseDate;
    FavouriteAdapter favouriteAdapter;

    public FavouriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_favourite, container, false);
        rv = view.findViewById(R.id.movieFavourite);
        error = view.findViewById(R.id.errorMessageFavourite);

        db = new FavouriteHelper(getContext());
        title = new Vector<>();
        overview = new Vector<>();
        path = new Vector<>();
        rating = new Vector<>();
        releaseDate = new Vector<>();

        StoreDataInArrays();
        Log.wtf("title", title.get(0));

        favouriteAdapter = new FavouriteAdapter(getContext());
        favouriteAdapter.setMovies(title, overview, path, rating, releaseDate);
        rv.setAdapter(favouriteAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        favouriteAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    void StoreDataInArrays(){
        Cursor cursor = db.getFavouriterData();
        if (cursor.getCount() != 0){
            error.setVisibility(View.GONE);
            while (cursor.moveToNext()){
                title.add(cursor.getString(1));
                overview.add(cursor.getString(2));
                path.add(cursor.getString(3));
                rating.add(cursor.getString(4));
                releaseDate.add(cursor.getString(5));
            }
        }
    }
}