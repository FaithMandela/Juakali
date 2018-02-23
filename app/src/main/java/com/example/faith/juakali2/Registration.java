package com.example.faith.juakali2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();
    }

    public void logIn(View view){
        Intent intent = new Intent(Registration.this,MainActivity.class);
        startActivity(intent);
    }
}
