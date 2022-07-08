package com.example.insightsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class login extends AppCompatActivity {
    TextView textview2;
    EditText editTextTextEmailAddress;
    EditText editTextTextPassword;
    Button button;
    TextView textview3;
    TextView signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button = findViewById(R.id.button);
        textview3 = findViewById(R.id.textView3);
        signup = findViewById(R.id.signup);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextTextEmailAddress.getText().toString();
                String password = editTextTextPassword.getText().toString();
                if (email.isEmpty()){
                    editTextTextEmailAddress.setError("Enter the email address");

                }
                if (password.isEmpty()){
                    editTextTextPassword.setError("Enter the password");
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.insightsapp.signup.class);
                startActivity(intent);
            }
        });

    }



}






