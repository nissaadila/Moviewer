package com.example.moviewer;

import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;


public class Home extends AppCompatActivity implements View.OnClickListener{

    TextView searchBar, errorMsg;
    Button searchBtn;
    String keyword;

    String title, rating, overview, path, published_date;
    RecyclerView mrv;

    Vector<Movie> movies = new Vector<>();
    MovieAdapter movieAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchBar = findViewById(R.id.search_bar);
        searchBtn = findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(this);
        errorMsg = findViewById(R.id.errorMessage);
        mrv = findViewById(R.id.movieView);
        movieAdapt = new MovieAdapter(this);
        movieAdapt.setMovies(movies);
        mrv.setAdapter(movieAdapt);
        mrv.setLayoutManager(new LinearLayoutManager(this));

//        searchData("Amazing Spiderman");
    }

    @Override
    public void onClick(View view) {
        if(view == searchBtn && searchBar.toString()!=null){
            keyword = searchBar.getText().toString();
            Log.v("test", keyword);
            searchData(keyword);
            Toast.makeText(this, "Searching for" + keyword, Toast.LENGTH_SHORT).show();
        }else if(view == searchBtn && searchBar.toString()==null){
            Toast.makeText(this, "Please Input Keyword", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId()==R.id.homeBtn){
            //direct to home
        }else if(view.getId()==R.id.favouriteBtn){
            //direct to fav
        }else if(view.getId()==R.id.profileBtn){
            //direct to profile
        }
    }

    public void searchData(String keyword){
        movies.clear();
        errorMsg.setVisibility(View.GONE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
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

                            Movie movie = new Movie(title, overview, path, rating, published_date);
                            movies.add(movie);
                        }
                        movieAdapt.notifyDataSetChanged();

                        for (Movie e: movies) {
                            Log.v("patpat", e.path.toString());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.v("hohohote", error.toString());
                }
        );
        requestQueue.add(request);
    }

}

