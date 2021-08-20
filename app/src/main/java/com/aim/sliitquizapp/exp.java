package com.aim.sliitquizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.aim.sliitquizapp.model.Question;
import com.aim.sliitquizapp.model.desc;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class exp extends AppCompatActivity {

    EditText et1;
    EditText et2;
    Button btnsave;
    DatabaseReference reff;
    desc des;




   // Button btnshow;
    Button btnins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        btnsave = (Button) findViewById(R.id.btnsave);
       // btnshow = (Button) findViewById(R.id.btnshow);
        btnins = (Button)findViewById(R.id.btnins);


        des = new desc();


        // FirebaseDatabase database;
        // DatabaseReference reff;

        reff = FirebaseDatabase.getInstance().getReference().child("Discription");

        //===================start intent show btn===================================

       /* btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlistview();
            }
        });*/


       //=======================finish intent show==========================================
        //======================start int to instru=========================================

        btnins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openLessonListActivity();
            }
        });


        //==========================finish intnt==============================================
        btnsave.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                des.setAnswer(et2.getText().toString().trim());
                des.setQuestion(et1.getText().toString().trim());


               // reff.child("Discription").setValue(des);
                reff.push().setValue(des);

                Toast.makeText(exp.this, "data inserted succesfully", Toast.LENGTH_LONG).show();
            }



        });


    }


    public void openlistview(){

        Intent intent = new Intent(this,listview.class);
        startActivity(intent);
    }


    public void openLessonListActivity(){

        Intent intent = new Intent(this,LessonListActivity.class);
        startActivity(intent);
    }


}

