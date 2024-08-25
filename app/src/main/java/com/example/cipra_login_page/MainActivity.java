package com.example.cipra_login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                validateSignIn(email, password);
            }
        });
    }

    private void validateSignIn(String email, String password) {
        if (email.equals("jared.leitner@gmail.com") && password.equals("Test123!*")) {
            performSignIn(email, password);
        } else {
            Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void performSignIn(String email, String password) {
        String url = "https://api.cipra.ai:5000/takehome/signin?email=" + email + "&password=" + password;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                        intent.putExtra("response", response);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }
}