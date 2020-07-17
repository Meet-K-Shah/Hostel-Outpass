package com.example.hosteloutpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FacultyActivity extends AppCompatActivity {

    EditText userid,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        login = findViewById(R.id.login);
        userid = findViewById(R.id.userid);
        password = findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(userid.getText().toString())){
                    Toast.makeText(FacultyActivity.this, "Pleas Enter User ID Address", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(FacultyActivity.this, "Pleas Enter Password", Toast.LENGTH_SHORT).show();
                }/*
                else if((userid.getText().toString() != "abc" ||  password.getText().toString() != "...")
                        || (userid.getText().toString() != "xyz" ||  password.getText().toString() != "xyz123")
                        || (userid.getText().toString() != "jay" ||  password.getText().toString() != "jay123")){
                    Toast.makeText(FacultyActivity.this, "User ID and Password doesn't match ", Toast.LENGTH_SHORT).show();
                }*/
                else {
                    Login();
                }

            }
        });

    }

    private void Login() {
        startActivity(new Intent(FacultyActivity.this,FacultyMainActivity.class));
    }
}
