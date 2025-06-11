package com.example.news_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView signupText;
    TextView loginText;
    Button loginButton; // Added Button for login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        signupText = findViewById(R.id.signup_text);
        loginText = findViewById(R.id.login_text);
        loginButton = findViewById(R.id.login_button);

        // Navigate to SignupActivity
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        // Navigate to NewsActivity via TextView (optional)
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, NewsActivity.class));
            }
        });

        // Navigate to NewsActivity via Login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, NewsActivity.class));
            }
        });
    }
}
