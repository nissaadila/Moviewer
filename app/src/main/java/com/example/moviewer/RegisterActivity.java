package com.example.moviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moviewer.Database.UserHelper;
import com.example.moviewer.Models.User;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email;
    Button btnRegister, btnToLogin;
    UserHelper userHelper;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        tvLogin.setOnClickListener(v -> {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
        });

        btnRegister.setOnClickListener(v -> {
            String usernameText = username.getText().toString();
            String passwordText = password.getText().toString();
            String emailText = email.getText().toString();

            User user = new User(usernameText, passwordText, emailText);
            userHelper.insert(user);


            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void init(){
        username = findViewById(R.id.edtUsernameRegister);
        email = findViewById(R.id.edtEmailRegister);
        password = findViewById(R.id.edtPasswordRegister);
        btnRegister = findViewById(R.id.buttonRegister);
        tvLogin = findViewById(R.id.tvLoginNow);
    }
}
