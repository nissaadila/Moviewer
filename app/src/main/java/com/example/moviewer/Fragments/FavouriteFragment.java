package com.example.moviewer.Fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


public class FavouriteFragment extends Fragment {

    TextView error;
    RecyclerView rv;

    FavouriteHelper db;
    ArrayList<String> title, overview, path, rating;
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
        title = new ArrayList<>();
        overview = new ArrayList<>();
        path = new ArrayList<>();
        rating = new ArrayList<>();

        StoreDataInArrays();

        favouriteAdapter = new FavouriteAdapter(getContext(), title, overview, path, rating);
        rv.setAdapter(favouriteAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
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
                title.add(cursor.getString(0));
                overview.add(cursor.getString(1));
                path.add(cursor.getString(2));
                rating.add(cursor.getString(3));
            }
        }
    }
}