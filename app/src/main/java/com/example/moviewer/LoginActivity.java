package com.example.moviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText email, password;
    Button bLogin;
    TextView tvRegistNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inisialisasi();
        bLogin.setOnClickListener(this);
        tvRegistNow.setOnClickListener(this);
    }

    void inisialisasi(){
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        bLogin = findViewById(R.id.buttonLogin);
        tvRegistNow = findViewById(R.id.textViewRegisterNow);
    }

    @Override
    public void onClick(View v) {
        String emaill = email.getText().toString();
        String paswordd = password.getText().toString();


        if(emaill.isEmpty() && paswordd.isEmpty()){

        }

    }
}