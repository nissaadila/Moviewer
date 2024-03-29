package com.example.moviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.database.Cursor;
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
    SharedPreferences sharedPreferences;

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
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(emailText.isEmpty()){
            email.setError("email cannot be empty");
            email.requestFocus();
            return;
        }

        if(passwordText.isEmpty()){
            password.setError("password cannot be empty");
            password.requestFocus();
            return;
        }

        User validasiuser = userHelper.auth(emailText,passwordText);

        if (validasiuser != null){
            editor.putInt("id", validasiuser.getId());
            editor.putString("email",emailText);
            editor.putString("password",passwordText);
            editor.putString("username", validasiuser.getUsername());
            editor.apply();
            Toast.makeText(this,"Success Login",Toast.LENGTH_LONG).show();
            Intent move = new Intent(LoginActivity.this, MainActivity.class);
            move.putExtra("user", validasiuser);
            startActivity(move);
            finish();
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
        sharedPreferences = getSharedPreferences("LOG_IN",MODE_PRIVATE);
    }


}
