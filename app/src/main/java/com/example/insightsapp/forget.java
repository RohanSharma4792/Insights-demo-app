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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget extends AppCompatActivity {

    EditText email;
    TextView login;
    Button submit;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget);
        email = findViewById(R.id.editTextTextEmailAddress2);
        submit = findViewById(R.id.button5);
        auth = FirebaseAuth.getInstance();
        login = findViewById(R.id.textView4);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(forget.this, login.class);
                startActivity(login);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }

            private void validateData() {
                String gettext = email.getText().toString();
                if (gettext.isEmpty()){
                    email.setError("required");
                }
                else{
                    forgets();
                }
            }

            private void forgets() {
                String gettext = email.getText().toString();
                auth.sendPasswordResetEmail(gettext)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(forget.this, login.class);
                                    Toast.makeText(forget.this, "check your email", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(forget.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}