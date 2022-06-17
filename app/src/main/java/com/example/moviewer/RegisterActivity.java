package com.example.moviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviewer.Database.UserHelper;
import com.example.moviewer.Models.User;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email;
    Button btnRegister;
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
            validate();
        });
    }

    private void validate(){
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();
        String emailText = email.getText().toString();

        if(usernameText.isEmpty()){
            username.setError("username cannot be empty");
            username.requestFocus();
            return;
        }

        if(usernameText.length() > 20){
            username.setError("username invalid");
            username.requestFocus();
            return;
        }

        if(emailText.isEmpty()){
            email.setError("email cannot be empty");
            email.requestFocus();
            return;
        }

        if(!emailText.endsWith(".com")){
            email.setError("email invalid");
            email.requestFocus();
            return;
        }

        if(passwordText.isEmpty()){
            password.setError("password cannot be empty");
            password.requestFocus();
            return;
        }

        Cursor cursor = userHelper.getUserData();

        if(cursor != null){
            User validateUser = userHelper.authUsername(usernameText);

            if (validateUser == null){
                User user = new User(usernameText, passwordText, emailText);
                userHelper.insert(user);


                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Log.wtf("validasiUser", validateUser.getUsername());
                Toast.makeText(this, "user is taken", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void init(){
        username = findViewById(R.id.edtUsernameRegister);
        email = findViewById(R.id.edtEmailRegister);
        password = findViewById(R.id.edtPasswordRegister);
        btnRegister = findViewById(R.id.buttonRegister);
        tvLogin = findViewById(R.id.tvLoginNow);
        userHelper = new UserHelper(this);
    }
}
