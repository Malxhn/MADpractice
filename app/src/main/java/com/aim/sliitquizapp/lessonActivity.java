/*
package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aim.sliitquizapp.model.Lesson;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class lessonActivity extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    Lesson l;
    DatabaseReference reff;

    private void clearControls(){

        e1.setText("");
        e2.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        e1.findViewById(R.id.e1);
        e2.findViewById(R.id.e2);
        b1.findViewById(R.id.b1);


        l = new Lesson();


        reff = FirebaseDatabase.getInstance().getReference().child("REF");

        try{
            if(TextUtils.isEmpty(e1.getText().toString()))
                Toast.makeText(getApplicationContext(),"enter lesson",Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(e2.getText().toString()))
                Toast.makeText(getApplicationContext(),"enter lesson",Toast.LENGTH_SHORT).show();
            else{
                l.setLess(e1.getText().toString().trim());
                l.setRef(e2.getText().toString().trim());

                reff.push().setValue(l);

                Toast.makeText(getApplicationContext(),"saved data",Toast.LENGTH_SHORT).show();
                clearControls();
            }

        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_SHORT).show();
        }
    }




}

*/



