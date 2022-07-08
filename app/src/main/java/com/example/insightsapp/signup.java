package com.example.insightsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class signup extends AppCompatActivity {
    EditText editTextTextPersonName;
    EditText editTextPhone2;
    EditText editTextTextEmailAddress3;
    EditText editTextTextPassword4;
    EditText editTextTextPassword5;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextPhone2 = findViewById(R.id.editTextPhone2);
        editTextTextEmailAddress3 = findViewById(R.id.editTextTextEmailAddress3);
        editTextTextPassword4 = findViewById(R.id.editTextTextPassword4);
        editTextTextPassword5 = findViewById(R.id.editTextTextPassword5);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextTextPersonName.getText().toString();
                String phone = editTextPhone2.getText().toString();
                String email = editTextTextEmailAddress3.getText().toString();
                String password = editTextTextPassword4.getText().toString();
                String cpassword = editTextTextPassword5.getText().toString();

                if (name.isEmpty()){
                    editTextTextPersonName.setError("enter the name");
                }
                if (phone.isEmpty()){
                    editTextPhone2.setError("enter the mobile number");
                }
                if (email.isEmpty()){
                    editTextTextEmailAddress3.setError("enter the email");
                }
                if (password.isEmpty()){
                    editTextTextPassword4.setError("enter the password");
                }
                if (cpassword.isEmpty()){
                    editTextTextPassword5.setError("enter the password");
                }
                if (password != cpassword){
                    editTextTextPassword5.setError("enter the password correctly");
                }
            }
        });
    }




    public void registerbutton(View view) {
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
        }


    }







