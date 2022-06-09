package com.example.moviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button hi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hi=findViewById(R.id.hi);
        hi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==hi){
            Intent i = new Intent(this, Home.class);
            startActivity(i);
        }
    }
}