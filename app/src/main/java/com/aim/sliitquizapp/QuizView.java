package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.aim.sliitquizapp.model.Question;

import java.util.List;

public class QuizView extends AppCompatActivity {

        private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_view);
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerview_quiz);

        new QuizDatabaseHelper().readquiz(new QuizDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Question> quizs, List<String> keys) {
                findViewById(R.id.loading_quiz_PB).setVisibility(View.GONE);
                new QuizRecyclerView_Config().setConfig(mRecyclerView,QuizView.this,quizs,keys);
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
