package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aim.sliitquizapp.model.Lesson;

import java.util.List;

public class LessonListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;


    private String key;
    private String answer;
    private String question;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

/*
        key = getIntent().getStringExtra("key");
        answer = getIntent().getStringExtra("answer");
        question = getIntent().getStringExtra("question");

*/




        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_lessons);
        new FirebaseDatabaseHelper().readLessons(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Lesson> lessons, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView,LessonListActivity.this,lessons,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
