package com.example.insightsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class dashboard extends AppCompatActivity {
    Button logout;
    ImageView prof;
    TextView name;
    TextView email;
    TextView mobile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        logout = findViewById(R.id.button3);
        prof = findViewById(R.id.imageView);
        name = findViewById(R.id.textView9);
        email = findViewById(R.id.textView10);
        mobile = findViewById(R.id.textView11);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(dashboard.this, login.class);
                Toast.makeText(dashboard.this, "signed out", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Data");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String atr = snapshot.getValue().toString();
                        String btr = snapshot.getValue().toString();
                        String ctr = snapshot.getValue().toString();

                        name.setText(atr);
                        email.setText(btr);
                        mobile.setText(ctr);




                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            })
        ;}
    }
