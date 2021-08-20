package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.aim.sliitquizapp.model.Question;
import com.aim.sliitquizapp.model.RecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AnswerActivity extends AppCompatActivity {

    ArrayList<Question> list = new ArrayList<Question>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent intent = getIntent();

        list = intent.getParcelableArrayListExtra(QuestionActivity.QLIST);


        //DatabaseReference databaseReference;

        //final List<Question> list = new ArrayList<>();

        final RecyclerView recyclerView;

        final RecyclerView.Adapter[] adapter = new RecyclerView.Adapter[1];

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(AnswerActivity.this));

        recyclerView.setAdapter(adapter[0]);

        adapter[0] = new RecyclerViewAdapter(AnswerActivity.this, list);

        recyclerView.setAdapter(adapter[0]);
/*
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Questions");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Question qus = dataSnapshot.getValue(Question.class);

                    list.add(qus);
                }

                adapter[0] = new RecyclerViewAdapter(AnswerActivity.this, list);

                recyclerView.setAdapter(adapter[0]);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });
*/
    }
}
