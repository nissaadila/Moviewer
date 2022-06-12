package com.example.moviewer.Fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviewer.Database.UserHelper;
import com.example.moviewer.LoginActivity;
import com.example.moviewer.Models.User;
import com.example.moviewer.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    EditText editTextUsername, editTextEmail;
    Button editUsername, editEmail, saveUsername, saveEmail, editPhoto, logOut;
    TextView username, email;
    ImageView profile;

    UserHelper uh;
    User curr_user;
    int curr_id=-1;

    Bitmap bitmap = null;
    byte image[];

    String curr_email, curr_password, curr_username;
    String new_email, new_username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        editTextUsername = view.findViewById(R.id.EditTextUsername);
        editTextEmail = view.findViewById(R.id.EditTextEmail);
        editUsername = view.findViewById(R.id.BtnEditUsername);
        editEmail = view.findViewById(R.id.BtnEditEmail);
        saveUsername = view.findViewById(R.id.BtnUsernameSave);
        saveEmail = view.findViewById(R.id.BtnEmailSave);
        editPhoto = view.findViewById(R.id.BtnProfilePic);
        logOut = view.findViewById(R.id.BtnLogOut);
        username = view.findViewById(R.id.TextUsername);
        email = view.findViewById(R.id.TextEmail);
        profile = view.findViewById(R.id.profilePic);

        editUsername.setOnClickListener(this);
        editEmail.setOnClickListener(this);
        saveEmail.setOnClickListener(this);
        saveUsername.setOnClickListener(this);
        editPhoto.setOnClickListener(this);
        logOut.setOnClickListener(this);


        SharedPreferences sp = getContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        curr_email = sp.getString("email", "");
        curr_password = sp.getString("password", "");
        curr_username = sp.getString("username", "");

 //       curr_id = uh.findID(curr_username, curr_email, curr_password);
        Log.v("currid", "id: " + curr_id);
//        curr_user = uh.findUser(curr_email, curr_password);
//
//        username.setText(curr_user.getUsername());
//        email.setText(curr_user.getEmail());

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == editUsername) {
            editTextUsername.setVisibility(View.VISIBLE);
            saveUsername.setVisibility(View.VISIBLE);
            editUsername.setVisibility(View.GONE);
            username.setVisibility(View.GONE);
        } else if (view == saveUsername) {
            editTextUsername.setVisibility(View.GONE);
            saveUsername.setVisibility(View.GONE);
            editUsername.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);

            if(editTextUsername.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Username cannot be empty!", Toast.LENGTH_SHORT).show();
            }else{
                new_username = editTextUsername.getText().toString();
                uh.updateUsername(curr_id, new_username);
            }
        } else if (view == editEmail) {
            editTextEmail.setVisibility(View.VISIBLE);
            saveEmail.setVisibility(View.VISIBLE);
            editEmail.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
        } else if (view == saveEmail) {
            editTextEmail.setVisibility(View.GONE);
            saveEmail.setVisibility(View.GONE);
            editEmail.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);

            if(editTextEmail.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Email cannot be empty!", Toast.LENGTH_SHORT).show();
            }else{
                //check email udah ada belom
                new_email = editTextEmail.getText().toString();
                if(uh.checkEmail(new_email)==1){
                    Toast.makeText(getContext(), "Email already exist!", Toast.LENGTH_SHORT).show();
                }else{
                    uh.updateUsername(curr_id, new_email);
                }
            }
        } else if (view == logOut) {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
        } else if (view == editPhoto) {
            chooseProfilePhoto();
        }
    }

    private void chooseProfilePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK && data !=null) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), selectedImage);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                image = baos.toByteArray();
                profile.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
