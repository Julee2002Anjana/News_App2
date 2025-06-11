package com.example.news_app;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserProfileActivity extends AppCompatActivity {

    private TextView usernameText, emailText;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private MaterialButton signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        usernameText = findViewById(R.id.username);
        emailText = findViewById(R.id.email_input);
        ImageView backButton = findViewById(R.id.back_button);
        ImageView goButton = findViewById(R.id.go_button);
        signOutButton = findViewById(R.id.signout_button);

        if (user != null) {
            usernameText.setText(user.getDisplayName() != null ? user.getDisplayName() : "No Username");
            emailText.setText(user.getEmail() != null ? user.getEmail() : "No Email");
        }

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, NewsActivity.class));
            finish();
        });

        goButton.setOnClickListener(v -> {
            AlertDialog.Builder choiceBuilder = new AlertDialog.Builder(this);
            choiceBuilder.setTitle("Edit Profile Info");
            String[] options = {"Edit Username", "Edit Email"};
            choiceBuilder.setItems(options, (dialog, which) -> {
                if (which == 0) {
                    showEditSingleFieldDialog("username");
                } else {
                    showEditSingleFieldDialog("email");
                }
            });
            choiceBuilder.show();
        });

        signOutButton.setOnClickListener(v -> showSignOutDialog());
    }

    private void showEditSingleFieldDialog(String field) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.edit_dialog, null);
        builder.setView(view);

        EditText inputUsername = view.findViewById(R.id.username);
        EditText inputEmail = view.findViewById(R.id.email_input);
        Button btnOk = view.findViewById(R.id.btnOk);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        TextView titleText = view.findViewById(R.id.dialog_title);

        AlertDialog dialog = builder.create();

        if (field.equals("username")) {
            inputEmail.setVisibility(View.GONE);
            inputUsername.setVisibility(View.VISIBLE);
            inputUsername.setText(usernameText.getText().toString());
            titleText.setText("Edit Username");
        } else {
            inputUsername.setVisibility(View.GONE);
            inputEmail.setVisibility(View.VISIBLE);
            inputEmail.setText(emailText.getText().toString());
            titleText.setText("Edit Email");
        }

        btnOk.setOnClickListener(v -> {
            if (field.equals("username")) {
                String newUsername = inputUsername.getText().toString().trim();
                if (newUsername.isEmpty()) {
                    inputUsername.setError("Username cannot be empty");
                    return;
                }

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newUsername)
                        .build();

                user.updateProfile(profileUpdates).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        usernameText.setText(newUsername);
                        Toast.makeText(this, "Username updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Username update failed", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                String newEmail = inputEmail.getText().toString().trim();
                if (newEmail.isEmpty()) {
                    inputEmail.setError("Email cannot be empty");
                    return;
                }

                user.updateEmail(newEmail).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        emailText.setText(newEmail);
                        Toast.makeText(this, "Email updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Email update failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            dialog.dismiss();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void showSignOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_signout, null);
        builder.setView(view);

        Button btnOk = view.findViewById(R.id.btnOk);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        AlertDialog dialog = builder.create();
        dialog.show();

        btnOk.setOnClickListener(v -> {
            mAuth.signOut();
            dialog.dismiss();
            startActivity(new Intent(UserProfileActivity.this, LoginActivity.class));
            finishAffinity();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());
    }
}
