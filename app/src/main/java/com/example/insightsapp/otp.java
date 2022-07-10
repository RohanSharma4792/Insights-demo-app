package com.example.insightsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {
    EditText otp;
    Button submit;
    String getotp;
    Button reotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
        otp = findViewById(R.id.editTextPhone);
        submit = findViewById(R.id.button2);
        reotp = findViewById(R.id.button4);
        getotp = getIntent().getStringExtra("s");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otp.getText().toString().isEmpty()) {
                    otp.setError("enter the otp");
                }
                if (getotp != null) {
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            getotp, otp.getText().toString()
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(otp.this, dashboard.class);
                                        Toast.makeText(otp.this, "otp verified", Toast.LENGTH_SHORT).show();
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(otp.this, "enter correct otp", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(otp.this, "No internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        reotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("s"),
                        50,
                        TimeUnit.SECONDS,
                        otp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(otp.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onCodeSent(@NonNull String newotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                getotp = newotp;
                                Toast.makeText(otp.this, "otp sent", Toast.LENGTH_SHORT).show();

                                finish();
                            }

        });

    }

});}}