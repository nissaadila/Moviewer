package com.example.moviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    EditText editTextUsername, editTextEmail;
    Button editUsername, editEmail, saveUsername, saveEmail, editPhoto, logOut;
    TextView username, email;
    ImageView profile;

    Bitmap bitmap = null;
    byte img[];

    void init(){
        editTextUsername = findViewById(R.id.EditTextUsername);
        editTextEmail = findViewById(R.id.EditTextEmail);
        editUsername = findViewById(R.id.BtnEditUsername);
        editEmail = findViewById(R.id.BtnEditEmail);
        saveUsername = findViewById(R.id.BtnUsernameSave);
        saveEmail = findViewById(R.id.BtnEmailSave);
        editPhoto = findViewById(R.id.BtnProfilePic);
        logOut = findViewById(R.id.BtnLogOut);
        username = findViewById(R.id.TextUsername);
        email = findViewById(R.id.TextEmail);
        profile = findViewById(R.id.profilePic);

        editUsername.setOnClickListener(this);
        editEmail.setOnClickListener(this);
        saveEmail.setOnClickListener(this);
        saveEmail.setOnClickListener(this);
        editPhoto.setOnClickListener(this);
        logOut.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();

        //set text email dan username dlu

    }

    @Override
    public void onClick(View view) {
        if(view == editUsername){
            editTextUsername.setVisibility(View.VISIBLE);
            saveUsername.setVisibility(View.VISIBLE);
            editUsername.setVisibility(View.GONE);
            username.setVisibility(View.GONE);
        }else if(view == saveUsername){
            editTextUsername.setVisibility(View.GONE);
            saveUsername.setVisibility(View.GONE);
            editUsername.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);
        }else if(view == editEmail){
            editTextEmail.setVisibility(View.VISIBLE);
            saveEmail.setVisibility(View.VISIBLE);
            editEmail.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
        }else if(view == saveEmail){
            editTextEmail.setVisibility(View.GONE);
            saveEmail.setVisibility(View.GONE);
            editEmail.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
        }else if(view == logOut){
            Intent i = new Intent(Profile.this, LoginActivity.class);
            startActivity(i);
        }else if(view == editPhoto){
            pickFromGallery();


        }
    }

    private void pickFromGallery(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        startActivityForResult(intent,0);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && data !=null) {
            Uri selectedImage = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
                img = bos.toByteArray();
                profile.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}