package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.aim.sliitquizapp.model.Feedback;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FeedbackListActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);
        mrecyclerView = (RecyclerView) findViewById(R.id.feedbackRecView);
        new FeedbackDatabaseHelper().readquiz(new FeedbackDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Feedback> quizs, List<String> keys) {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                new FeedbackRecyclerViewConfig().setConfig(mrecyclerView,FeedbackListActivity.this,quizs,keys);
            }

            @Override
            public void DataISInserted() {

            }

            @Override
            public void DataIsUpdatd() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
