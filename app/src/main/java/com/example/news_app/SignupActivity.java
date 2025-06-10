package com.example.news_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    TextView loginText;
    Button signupButton; // Referencing signup_button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // Make sure the layout has correct IDs

        loginText = findViewById(R.id.login_text);
        signupButton = findViewById(R.id.signup_button); // Make sure this ID exists in the layout

        // Navigate to LoginActivity when "Login" text is clicked
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Navigate to NewsActivity when "Sign Up" button is clicked
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, NewsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
