package com.example.hosteloutpass;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FacultyfirebaseActivity extends AppCompatActivity {

    private EditText name,enroll,gmail,password;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_faculty);

        name = findViewById(R.id.name);
        enroll = findViewById(R.id.enroll);
        gmail = findViewById(R.id.gmail);
        password = findViewById(R.id.password);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String,Object> map = new HashMap<>();
                map.put("name",name.getText().toString());
                map.put("enroll",enroll.getText().toString());
                map.put("gmail",gmail.getText().toString());
                map.put("password",password.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("Post").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("jfbvkj", "onComplete: ");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("jfbvkj", "onFailure: "+e.toString());
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("jfbvkj", "onSuccess: ");
                    }
                });


                TextView name = (TextView) findViewById(R.id.name);
                name.setText("");

                TextView enroll = (TextView) findViewById(R.id.enroll);
                enroll.setText("");

                TextView gmail = (TextView) findViewById(R.id.gmail);
                gmail.setText("");

                TextView password = (TextView) findViewById(R.id.password);
                password.setText("");



            }
        });

    }
}