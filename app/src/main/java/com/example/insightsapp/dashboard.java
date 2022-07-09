package com.example.insightsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class dashboard extends AppCompatActivity {
    Button logout;
    ImageView prof;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        logout = findViewById(R.id.button3);
        prof = findViewById(R.id.imageView);





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
    }
}