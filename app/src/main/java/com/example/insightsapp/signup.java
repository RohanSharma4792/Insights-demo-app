package com.example.insightsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class signup extends AppCompatActivity {
    EditText editTextTextPersonName;
    EditText editTextPhone2;
    EditText editTextTextEmailAddress3;
    EditText editTextTextPassword4;
    EditText editTextTextPassword5;
    Button register;



    FirebaseAuth firebaseAuth;


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
        firebaseAuth = FirebaseAuth.getInstance();





            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = editTextTextPersonName.getText().toString();
                    String phone = editTextPhone2.getText().toString();
                    String email = editTextTextEmailAddress3.getText().toString();
                    String password = editTextTextPassword4.getText().toString();
                    String cpassword = editTextTextPassword5.getText().toString();



                    if (!name.isEmpty()){
                        editTextTextPersonName.setError(null);
                        if (!phone.isEmpty()){
                            editTextPhone2.setError(null);
                            if (!email.isEmpty()){
                                editTextTextEmailAddress3.setError(null);
                                if (!password.isEmpty()){
                                    editTextTextPassword4.setError(null);
                                    if (!cpassword.isEmpty()){
                                        editTextTextPassword5.setError(null);


                                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                            @Override
                                            public void onSuccess(AuthResult authResult) {
                                                HashMap<String, Object> data = new HashMap<>();
                                                data.put("Name", name);
                                                data.put("Email", email);
                                                data.put("Password", password);
                                                data.put("Phone Number", phone);
                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Data");
                                                databaseReference.child(name).setValue(data);

                                                Intent intent = new Intent(signup.this, dashboard.class);
                                                Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                                startActivity(intent);
                                                finish();

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(signup.this, "Failure", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }else {
                                        editTextTextPassword5.setError("confirm your password");
                                    }
                                }else {
                                    editTextTextPassword4.setError("enter the password");
                                }
                            }else {
                                editTextTextEmailAddress3.setError("enter the email");
                            }
                        }else{
                            editTextPhone2.setError("enter the phone no.");
                        }

                    }
                    else {
                        editTextTextPersonName.setError("enter the name");
                    }
                }
            });






//



            }

    }















