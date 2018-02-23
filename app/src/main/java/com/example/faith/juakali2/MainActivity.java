package com.example.faith.juakali2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etPhone, etPassword;
    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        InputFilter [] inputFilters = new InputFilter[1];
        inputFilters[0] = new InputFilter.LengthFilter(10);

        etPhone = (EditText)findViewById(R.id.etPhone);
        etPassword = (EditText)findViewById(R.id.etPassword);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvRegister = (TextView)findViewById(R.id.tvRegister);

        etPhone.setFilters(inputFilters);
    }

    public void logIn(View view){
        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();



        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
    }

    public void register(View view){
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }

    public void send(){

    }
}
