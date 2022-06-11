package com.example.moviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviewer.Database.UserHelper;
import com.example.moviewer.Models.User;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button bLogin;
    TextView tvRegistNow;
    UserHelper userHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inisialisasi();
        bLogin.setOnClickListener(v -> {
            validasi();
        });
        tvRegistNow.setOnClickListener(v ->{
            Intent j = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(j);
        });
    }

    private void validasi(){
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        User validasiuser = userHelper.auth(emailText,passwordText);
        if (validasiuser != null){
            Intent move = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(move);
            Toast.makeText(this,"Success Login",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "User not found",Toast.LENGTH_LONG).show();
        }

    }

    private void inisialisasi(){
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        bLogin = findViewById(R.id.buttonLogin);
        tvRegistNow = findViewById(R.id.textViewRegisterNow);
        userHelper = new UserHelper(this);
    }


}
