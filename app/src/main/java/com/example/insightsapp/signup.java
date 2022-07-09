package com.example.insightsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class signup extends AppCompatActivity {
    EditText editTextTextPersonName;
    EditText editTextPhone2;
    EditText editTextTextEmailAddress3;
    EditText editTextTextPassword4;
    EditText editTextTextPassword5;
    Button register;
    ImageView image;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextPhone2 = findViewById(R.id.editTextPhone2);
        editTextTextEmailAddress3 = findViewById(R.id.editTextTextEmailAddress3);
        editTextTextPassword4 = findViewById(R.id.editTextTextPassword4);
        editTextTextPassword5 = findViewById(R.id.editTextTextPassword5);
        image = findViewById(R.id.imageView2);
        register = findViewById(R.id.register);
        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();










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

                                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                                        "+91" + editTextPhone2.getText().toString(),
                                                        5,
                                                        TimeUnit.SECONDS,
                                                        signup.this,
                                                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                                            @Override
                                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                                            }

                                                            @Override
                                                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                                                Toast.makeText(signup.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                            }

                                                            @Override
                                                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                                super.onCodeSent(s, forceResendingToken);
                                                                Intent intent = new Intent(signup.this, otp.class);
                                                                Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                                                intent.putExtra("phone", editTextPhone2.getText().toString());
                                                                intent.putExtra("s", s);
                                                                startActivity(intent);
                                                                finish();
                                                            }
                                                        }
                                                );





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



        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1000);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageuri = data.getData();
                image.setImageURI(imageuri);
                uploadfirebase(imageuri);

            }
        }
    }

    private void uploadfirebase( Uri imageuri) {
        StorageReference fileref = storageReference.child("profile.jpg");
        fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(signup.this, "image uploaded", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signup.this, "upload failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
















