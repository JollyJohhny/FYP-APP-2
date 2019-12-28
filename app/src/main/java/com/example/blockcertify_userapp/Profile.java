package com.example.blockcertify_userapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void Logout(View v){
        FirebaseAuth.getInstance().signOut(); //End user session
        startActivity(new Intent(Profile.this, HomePage.class)); //Go back to home page
        finish();
    }

    public void ChangePassword(View v){
        Intent intent2 = new Intent(getBaseContext(), ChangePassword.class);
        startActivity(intent2);
    }

    public void ScanProduct(View v){
        Intent intent2 = new Intent(getBaseContext(), CameraScan.class);
        startActivity(intent2);
    }
}
