package com.example.hosteloutpass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hosteloutpass.DataModel.Outpass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentRequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText name, enrollment_no, sem, room_no, out_date, out_time, student_contact, parent_contact, parent_consent, reason;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private Button apply;
    private int selectedId;

    private String spin, radio;

    private DatabaseReference input, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_request);


        findbyid();

        radioGroup.clearCheck();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = group.findViewById(checkedId);

                if (radioButton.getText().toString() == "Boy") radio = "boy";
                if (radioButton.getText().toString() == "Girl") radio = "girl";

            }
        });

        List<String> categories = new ArrayList<>();
        categories.add("Deepak");
        categories.add("Bharto");
        categories.add("Jeegishow");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkout();

            }
        });


    }


    //    **************************    On Create Method Over Here ... New Method are going to be Made   ************************************


    private void findbyid() {

        name = findViewById(R.id.name);
        enrollment_no = findViewById(R.id.enroll);
        sem = findViewById(R.id.sem);
        room_no = findViewById(R.id.roomno);
        out_date = findViewById(R.id.outdate);
        out_time = findViewById(R.id.outtime);
        student_contact = findViewById(R.id.stucontact);
        parent_contact = findViewById(R.id.parentscontact);
        parent_consent = findViewById(R.id.parentsconsent);
        reason = findViewById(R.id.reason);
        radioGroup = findViewById(R.id.radiogroup);
        spinner = findViewById(R.id.spinner);
        apply = findViewById(R.id.apply);

        spinner.setOnItemSelectedListener(this);
    }


    private void checkout() {

        selectedId = radioGroup.getCheckedRadioButtonId();

        if (TextUtils.isEmpty(name.getText().toString())) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(enrollment_no.getText().toString())) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Enrollment Number ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(sem.getText().toString())) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter SEM", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(room_no.getText().toString())) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Room Number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(out_date.getText().toString())) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Out Date ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(out_time.getText().toString())) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Out Time", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(student_contact.getText().toString())) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Student Contact Number", Toast.LENGTH_SHORT).show();
        } else if ((student_contact.getText().toString().length()) != 10) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Contact Number Properly", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(parent_contact.getText().toString())) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Parent Contact Number", Toast.LENGTH_SHORT).show();
        } else if ((parent_contact.getText().toString().length()) != 10) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Contact Number Properly", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(parent_consent.getText().toString())) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Parent Consent", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(reason.getText().toString())) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Reason... ", Toast.LENGTH_SHORT).show();
        } else if (selectedId == -1) {
            Toast.makeText(StudentRequestActivity.this, "Please Enter Select a Radio Button ", Toast.LENGTH_SHORT).show();
        } else {
            apply();
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        spin = item;


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    // **********************************    DATA BASE ME STORE KARANE KA TIME AA GAYA    ***************************************
    private void apply() {

        //  WE HAVE TO IMPLEMENT A METHOD IN WHICH WE CAN CHECK THAT WHETHER THIS STUDENT HAS PUT REQUEST ALREADY OR NOT...........
        //FROM DATA BASE WE CAN CHECK THIS OUT......

        //Write a query for above......and set the value of temp = 0 if already presents.....


        Outpass map = new Outpass(name.getText().toString().toLowerCase(),
                enrollment_no.getText().toString(),
                sem.getText().toString(),
                room_no.getText().toString(),
                out_date.getText().toString(),
                out_time.getText().toString(),
                student_contact.getText().toString(),
                parent_contact.getText().toString(),
                parent_consent.getText().toString(),
                reason.getText().toString(),
                radio,
                spin,
                " ");



        /*
        Map<String, Object> map = new HashMap<>();
        map.put("Name", name.getText().toString().toLowerCase());
        map.put("Enrollment Number", enrollment_no.getText().toString());
        map.put("Semester", sem.getText().toString());
        map.put("Room No", room_no.getText().toString());
        map.put("Out Date", out_date.getText().toString());
        map.put("Out Time", out_time.getText().toString());
        map.put("Student Contact", student_contact.getText().toString());
        map.put("Parents Contact", parent_contact.getText().toString());
        map.put("Parents Consent", parent_consent.getText().toString());
        map.put("Reason", reason.getText());
        map.put("Gender", radio);
        map.put("Faculty", spin);
        map.put("Output", " ");

         */



        FirebaseDatabase.getInstance().getReference()
                .child("Input").push()
                .setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("abc", "onCompelete");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("abc", "onFailuer");
                Toast.makeText(StudentRequestActivity.this, "Your Request Fail", Toast.LENGTH_SHORT).show();
            }
        });


        Toast.makeText(StudentRequestActivity.this, "Your Request is Registered Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(StudentRequestActivity.this, LastResultActivity.class));

    }

}









        /*
        ............QUERY HERE...........
         */

        /*

        user = FirebaseDatabase.getInstance().getReference().child("Users");
        Query q = user.orderByChild("name").equalTo(name.getText().toString().toLowerCase());

        if(q == null){

            Toast.makeText(StudentRequestActivity.this,"Write a correct name that you have registered", Toast.LENGTH_LONG).show();

        }else {

            input = FirebaseDatabase.getInstance().getReference().child("Input");
            Query query = input.orderByChild("Name").equalTo(name.getText().toString().toLowerCase());
            if (query != null) temp = false;


            if (temp == true) {

                Toast.makeText(StudentRequestActivity.this, "You have already make a Request", Toast.LENGTH_LONG).show();

            } else {

                //From here we have to store a information in data base........

                Map<String, Object> map = new HashMap<>();
                map.put("Name", name.getText().toString().toLowerCase());
                map.put("Enrollment Number", enrollment_no.getText().toString());
                map.put("Semester", sem.getText().toString());
                map.put("Room No", room_no.getText().toString());
                map.put("Out Date", out_date.getText().toString());
                map.put("Out Time", out_time.getText().toString());
                map.put("Student Contact", student_contact.getText().toString());
                map.put("Parents Contact", parent_contact.getText().toString());
                map.put("Parents Consent", parent_consent.getText().toString());
                map.put("Reason", reason.getText());
                map.put("Gender", radio);
                map.put("Faculty", spin);
                map.put("Output", " ");


                FirebaseDatabase.getInstance().getReference()
                        .child("Input").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("abc", "onCompelete");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("abc", "onFailuer");
                        Toast.makeText(StudentRequestActivity.this, "Your Request Fail", Toast.LENGTH_SHORT).show();
                    }
                });


                Toast.makeText(StudentRequestActivity.this, "Your Request is Registered Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StudentRequestActivity.this, LastResultActivity.class));

            }

        }

        */





