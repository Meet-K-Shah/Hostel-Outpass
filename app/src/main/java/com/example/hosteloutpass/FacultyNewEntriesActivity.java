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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hosteloutpass.DataModel.Outpass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FacultyNewEntriesActivity extends AppCompatActivity {

    EditText name,email,password,enrollment_no;
    Button registration,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_new_entries);


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        enrollment_no = findViewById(R.id.enrollment_no);
        registration = findViewById(R.id.register);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(FacultyNewEntriesActivity.this, "Pleas Enter Email Address", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    Toast.makeText(FacultyNewEntriesActivity.this, "Pleas Enter valid Email Address", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(FacultyNewEntriesActivity.this, "Pleas Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().length()<6){
                    Toast.makeText(FacultyNewEntriesActivity.this, "Pleas Enter 6 or more than digit password", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(FacultyNewEntriesActivity.this, "Pleas Enter Password", Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(enrollment_no.getText().toString())){
                    Toast.makeText(FacultyNewEntriesActivity.this, "Pleas Enter Enrollment Number", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().length() != 11){
                    Toast.makeText(FacultyNewEntriesActivity.this, "Pleas Enter Correct Enrollment Number", Toast.LENGTH_SHORT).show();
                }
                else {
                    registration();
                }

            }
        });




    }

    private void registration() {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        Map<String,Object> map = new HashMap<>();
                        map.put("email",email.getText().toString());
                        map.put("password",password.getText().toString());
                        map.put("name",name.getText().toString().toLowerCase());
                        map.put("enrollment no",enrollment_no.getText().toString());

                        Outpass data = new Outpass( );

                        data.setName(name.getText().toString().toLowerCase());
                        data.setEnrollment_no(enrollment_no.getText().toString());

                        FirebaseDatabase.getInstance().getReference()
                                .child("Users")
                                .child(FirebaseAuth.getInstance().getUid())
                                .setValue(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(FacultyNewEntriesActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(FacultyNewEntriesActivity.this, FacultyMainActivity.class));
                                        finish();
                                    }
                                });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FacultyNewEntriesActivity.this, "Failed :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }




}
