package com.example.hosteloutpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FacultyMainActivity extends AppCompatActivity {

    Button entries,request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_main);

        entries = findViewById(R.id.entries);
        request = findViewById(R.id.request);

        entries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(FacultyMainActivity.this,FacultyNewEntriesActivity.class));
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FacultyMainActivity.this,FacultyNewRequestActivity.class));
            }
        });

    }
}
