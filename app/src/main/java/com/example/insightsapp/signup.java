package com.example.insightsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {
    EditText editTextTextPersonName;
    EditText editTextPhone2;
    EditText editTextTextEmailAddress3;
    EditText editTextTextPassword4;
    EditText editTextTextPassword5;
    Button register;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


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

                                        firebaseDatabase = FirebaseDatabase.getInstance();
                                        reference = firebaseDatabase.getReference("data");

                                        String name_s = editTextTextPersonName.getText().toString();
                                        String phone_S = editTextPhone2.getText().toString();
                                        String email_s = editTextTextEmailAddress3.getText().toString();
                                        String password_s = editTextTextPassword4.getText().toString();

                                        storingdata storingdatas = new storingdata(name_s, phone_S, email_s, password_s);
                                        reference.child(name_s).setValue(storingdatas);

                                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), otp.class);
                                        startActivity(intent);




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






//                if (password!=cpassword){
//                    editTextTextPassword5.setError("enter the password correctly");
//                }
//                else{



            }

    }















