package com.example.faith.juakali2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Registration extends AppCompatActivity {
    EditText name,phone,email,password,confirm;
    Button button;
    ProgressDialog progressDialog;
    SweetAlertDialog sweetAlertDialog;

   // String insertUrl = "http://192.168.0.167/Projects/juakali/registration.php";
   //static String insertUrl = "http://192.168.0.167/Projects/juakali";
    static String insertUrl = "http://192.168.43.190/Projects/juakali";

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        name = (EditText)findViewById(R.id.etName);
        phone = (EditText)findViewById(R.id.etPhone);
        email = (EditText)findViewById(R.id.etEmail);
        confirm = (EditText)findViewById(R.id.etConfirm);
        password = (EditText)findViewById(R.id.etPassword);
        button = (Button)findViewById(R.id.btnRegister);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        progressDialog = new ProgressDialog(Registration.this);

    }

    public void logIn(View view){
        Intent intent = new Intent(Registration.this,MainActivity.class);
        startActivity(intent);
    }

    //Send data to database
    public void register(View view){
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        final String fullName = name.getText().toString();
        final String phoneNumber = phone.getText().toString();
        final String u_email = email.getText().toString();
        final String u_password = password.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertUrl+"/registration.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.e("BASE111", response);

                Intent intent = new Intent(Registration.this,MainActivity.class);
                intent.putExtra("phone",phoneNumber);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String, String>();
                parameters.put("fullName",fullName);
                parameters.put("email",u_email);
                parameters.put("phone",phoneNumber);
                parameters.put("password",u_password);

                return parameters;
            }
        };
        requestQueue.add(stringRequest);
    }
}
