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
import com.example.moviewer.Movie;
import com.example.moviewer.R;

import java.util.ArrayList;
import java.util.Vector;


public class FavouriteFragment extends Fragment {

    TextView error;
    RecyclerView rv;

    FavouriteHelper db;
    Vector<Movie> movies;
    FavouriteAdapter favouriteAdapter;
    int curr_id;

    public FavouriteFragment(int user_id_logged) {
        this.curr_id = user_id_logged;
        Log.wtf("currid", "id: " + curr_id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_favourite, container, false);
        rv = view.findViewById(R.id.movieFavourite);
        error = view.findViewById(R.id.errorMessageFavourite);

        db = new FavouriteHelper(getContext());
        movies = new Vector<>();
        movies = db.getFavouriterData(curr_id);
        if(movies.isEmpty()){
        } else{
            error.setVisibility(View.GONE);
        }

        favouriteAdapter = new FavouriteAdapter(getContext());
        favouriteAdapter.setMovies(movies);
        rv.setAdapter(favouriteAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        favouriteAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}