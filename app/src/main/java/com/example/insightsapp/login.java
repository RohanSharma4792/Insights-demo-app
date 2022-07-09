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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class login extends AppCompatActivity {
    EditText editTextTextEmailAddress;
    EditText editTextTextPassword;
    Button button;
    TextView textview3;
    TextView signtext;
    FirebaseAuth firebaseAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(login.this, dashboard.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button = findViewById(R.id.button);
        textview3 = findViewById(R.id.textView3);
        signtext = findViewById(R.id.signup);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        firebaseAuth = FirebaseAuth.getInstance();


        signtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextTextEmailAddress.getText().toString();
                String password = editTextTextPassword.getText().toString();



                if (!email.isEmpty()) {
                    editTextTextEmailAddress.setError(null);
                    if (!password.isEmpty()) {
                        editTextTextPassword.setError(null);


                         firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                             @Override
                             public void onSuccess(AuthResult authResult) {
                                 Intent intent = new Intent(login.this, dashboard.class);
                                 Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                 startActivity(intent);
                                 finish();

                             }

                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Toast.makeText(login.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }
                         });



                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("data");






                    }

                }
            }
        });
    }
}


