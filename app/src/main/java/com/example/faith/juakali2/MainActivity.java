package com.example.faith.juakali2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etPhone, etPassword;
    Button btnLogin;
    TextView tvRegister;
    RequestQueue requestQueue;
    Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        InputFilter [] inputFilters = new InputFilter[1];
        inputFilters[0] = new InputFilter.LengthFilter(10);

        String phoneNumber = getIntent().getStringExtra("phone");

        etPhone = (EditText)findViewById(R.id.etPhone);

        etPassword = (EditText)findViewById(R.id.etPassword);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvRegister = (TextView)findViewById(R.id.tvRegister);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        map = new HashMap<String, String>();

        etPhone.setFilters(inputFilters);
    }

    public void logIn(View view){
        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();

        JSONObject jsonObject = new JSONObject();
        map.put("userPhone", phone);
        map.put("password", password);

        String url = Registration.insertUrl+"/login.php";

        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);

        /*JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(map),
                new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("BASE200 :"+response.toString());

                            JSONObject json = response;
                            try {
                                JSONArray jsonArray = response.getJSONArray("customer");
                                System.out.println("BASE300 :"+jsonArray.toString());

                                if (jsonArray.length()==0){
                                    Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();
                                }else {
                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                    startActivity(intent);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("BASE100 :"+error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);*/
    }

    public void register(View view){
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }

    public void send(){
    }
}
