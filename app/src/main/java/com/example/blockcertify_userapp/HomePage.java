package com.example.blockcertify_userapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void Login(View v){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void Register(View v){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
