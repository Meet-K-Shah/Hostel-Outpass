package com.example.hosteloutpass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentActivity extends AppCompatActivity {
    EditText email,password;
    TextView registration;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        registration = findViewById(R.id.registration);


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentActivity.this, "Please contact a faculty for registration ", Toast.LENGTH_LONG).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(StudentActivity.this, "Pleas Enter Email Address", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    Toast.makeText(StudentActivity.this, "Pleas Enter valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(StudentActivity.this, "Pleas Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().length()<6){
                    Toast.makeText(StudentActivity.this, "Pleas Enter 6 or more than digit password", Toast.LENGTH_SHORT).show();
                }
                else {
                    login();
                }
            }
        });


    }

    private void login() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(StudentActivity.this, "Login Successful.... ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(StudentActivity.this, StudentRequestActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(StudentActivity.this, "Failed :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
