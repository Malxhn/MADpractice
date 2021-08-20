package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aim.sliitquizapp.model.RecyclerViewAdapter;
import com.aim.sliitquizapp.model.ScoreViewAdapter;
import com.aim.sliitquizapp.model.Statistics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        DatabaseReference databaseReference;

        final List<Statistics> list = new ArrayList<>();

        final RecyclerView recyclerView;

        final RecyclerView.Adapter[] adapter = new RecyclerView.Adapter[1];

        recyclerView = findViewById(R.id.rvStat);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(StatisticsActivity.this));

        recyclerView.setAdapter(adapter[0]);

        adapter[0] = new ScoreViewAdapter(StatisticsActivity.this, list);

        recyclerView.setAdapter(adapter[0]);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Statistics");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Statistics st = dataSnapshot.getValue(Statistics.class);

                    list.add(st);
                }

                adapter[0] = new ScoreViewAdapter(StatisticsActivity.this, list);

                recyclerView.setAdapter(adapter[0]);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });
    }
}
