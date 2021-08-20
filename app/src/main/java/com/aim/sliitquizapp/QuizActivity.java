package com.aim.sliitquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aim.sliitquizapp.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {


    Spinner subspin;

    EditText txtque,txtanswa,txtanswb,txtanswc,txtanswd,txtanswer;

    Button btncreate,btnshow;
    DatabaseReference dbref;
    long maxid=0;

    Question quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        subspin=findViewById(R.id.spinsub);

        txtque=findViewById(R.id.txtq);
        txtanswa=findViewById(R.id.txta);
        txtanswb=findViewById(R.id.txtb);
        txtanswc=findViewById(R.id.txtc);
        txtanswd=findViewById(R.id.txtd);
        txtanswer=findViewById(R.id.txtanswer);

        btncreate=findViewById(R.id.btnupdate);
        btnshow=findViewById(R.id.btnDelete);



        quiz= new Question();
       //dbref=FirebaseDatabase.getInstance().getReference().child("Mc");




        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref=FirebaseDatabase.getInstance().getReference().child(subspin.getSelectedItem().toString());
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                            maxid=(dataSnapshot.getChildrenCount());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                quiz.setQuestion(txtque.getText().toString().trim());
                quiz.setOption1(txtanswa.getText().toString().trim());
                quiz.setOption2(txtanswb.getText().toString().trim());
                quiz.setOption3(txtanswc.getText().toString().trim());
                quiz.setOption4(txtanswd.getText().toString().trim());
                quiz.setAnswer(txtanswer.getText().toString().trim());

                //insert into db
                //dbref.push().setValue(quiz);
                dbref.child(String.valueOf(maxid+1)).setValue(quiz);

                Toast.makeText(getApplicationContext(), "Data save successfully", Toast.LENGTH_SHORT).show();

            }
        });




        List<String>Subject=new ArrayList<>();
        Subject.add(0,"Subject");
        Subject.add("IP");
        Subject.add("MC");
        Subject.add("SE");
        Subject.add("IWT");
        Subject.add("DMS");
        Subject.add("OOC");
        Subject.add("MADD");


        //Style and populate the spinner
        ArrayAdapter<String>dataAdapter;
        dataAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Subject);

        //dropdownlayout
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        subspin.setAdapter(dataAdapter);

        subspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(adapterView.getItemAtPosition(i).equals("Subject"))
                {
                    //do nothing
                }
                else
                {
                    //on selecting item
                    String item=adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this,QuizView.class);
                startActivity(intent);
            }
        });


    }
}
