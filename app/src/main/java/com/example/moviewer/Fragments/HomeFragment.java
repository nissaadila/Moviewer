package com.example.moviewer.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviewer.Movie;
import com.example.moviewer.MovieAdapter;
import com.example.moviewer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class HomeFragment extends Fragment implements View.OnClickListener{

    TextView searchBar, errorMsg;
    Button searchBtn;
    String keyword;
    int curr_id;

    String title, rating, overview, path, published_date;
    RecyclerView mrv;

    Vector<Movie> movies = new Vector<>();
    MovieAdapter movieAdapt;

    public HomeFragment (int user_id_logged) {
        this.curr_id = user_id_logged;
        Log.wtf("currid", "id: " + curr_id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        searchBar = view.findViewById(R.id.search_bar);
        searchBtn = view.findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(this);
        errorMsg = view.findViewById(R.id.errorMessage);
        mrv = view.findViewById(R.id.movieView);

        initData();
        movieAdapt = new MovieAdapter(getContext());
        movieAdapt.setMovies(movies);
        mrv.setAdapter(movieAdapt);
        mrv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if(view == searchBtn && searchBar.toString()!=null){
            keyword = searchBar.getText().toString();
            Log.v("test", keyword);
            searchData(keyword);
            Toast.makeText(getActivity(), "Searching for " + keyword, Toast.LENGTH_SHORT).show();
        }else if(view == searchBtn && searchBar.toString()==null){
            Toast.makeText(getActivity(), "Please Input Keyword", Toast.LENGTH_SHORT).show();
        }
    }
    public void searchData(String keyword){
        movies.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        final String api_key = "6cb3ffbaf1d68d170e4b832518a115c5";
        final String url = "https://api.themoviedb.org/3/search/movie?api_key=" + api_key + "&query=" + keyword;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        JSONArray array = response.getJSONArray("results");
                        for(int i=0; i<array.length(); i++){
                            JSONObject object = array.getJSONObject(i);

                            title = object.getString("original_title");
                            rating = object.getString("vote_average");
                            overview = object.getString("overview");
                            path = "https://image.tmdb.org/t/p/w500" + object.getString("poster_path");
                            published_date = object.getString("release_date");

                            Movie movie = new Movie(curr_id, title, overview, path, rating, published_date);
                            movies.add(movie);
                        }
                        movieAdapt.notifyDataSetChanged();
                        if(movies.size()>0){
                            errorMsg.setVisibility(View.GONE);
                        }else if(movies.size()==0){
                            errorMsg.setVisibility(View.VISIBLE);
                        }

                        for (Movie e: movies) {
                            Log.v("success", e.getPath());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.v("error", error.toString());
                }
        );
        requestQueue.add(request);
    }

    public void initData(){
        movies.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        final String api_key = "6cb3ffbaf1d68d170e4b832518a115c5";
        final String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + api_key;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        JSONArray array = response.getJSONArray("results");
                        for(int i=0; i<array.length(); i++){
                            JSONObject object = array.getJSONObject(i);

                            title = object.getString("original_title");
                            rating = object.getString("vote_average");
                            overview = object.getString("overview");
                            path = "https://image.tmdb.org/t/p/w500" + object.getString("poster_path");
                            published_date = object.getString("release_date");

                            Movie movie = new Movie(curr_id, title, overview, path, rating, published_date);
                            movies.add(movie);
                        }

                        if(movies.size()>0){
                            errorMsg.setVisibility(View.GONE);
                        }else if(movies.size()==0){
                            errorMsg.setVisibility(View.VISIBLE);
                        }

                        for (Movie e: movies) {
                            Log.v("success", e.getPath());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.v("error", error.toString());
                }
        );
        requestQueue.add(request);
    }

}