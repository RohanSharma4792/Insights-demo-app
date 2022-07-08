package com.example.insightsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class login extends AppCompatActivity {
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


                if (!email.isEmpty()) {
                    editTextTextEmailAddress.setError(null);
                    if (!password.isEmpty()) {
                        editTextTextPassword.setError(null);

//                        final String named = editTextTextEmailAddress.getText().toString();
//                        final String passed = editTextTextPassword.getText().toString();
//
//
//                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//                        DatabaseReference databaseReference = firebaseDatabase.getReference("data");
//
//                        Query checking = databaseReference.orderByChild("name").equalTo(named);
//
//                        checking.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                                if (snapshot.exists()) {
//                                    editTextTextEmailAddress.setError(null);
//                                    String pass = snapshot.child(named).child("password").getValue(String.class);
//                                    assert pass != null;
//                                    if (pass.equals(passed)) {
//                                        editTextTextEmailAddress.setError(null);
//
//                                        Toast.makeText(getApplicationContext(), "login successful", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(getApplicationContext(), dashboard.class);
//                                        startActivity(intent);
//                                        finish();
                                    } else {
                                        editTextTextPassword.setError("wrong password");
                                    }
                                } else {
                                    editTextTextEmailAddress.setError("invalid email");
                                }


                            }

//
//
//

                    });
                        signup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                        Intent intent = new Intent(getApplicationContext(), signup.class);
                                        startActivity(intent);
                            }
                        });

                }

            }


