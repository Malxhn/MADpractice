package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aim.sliitquizapp.model.Feedback;

import java.util.List;

public class FeedbackDetailsActivity extends AppCompatActivity {

    private EditText updateFeedback;
    private Button btnUpdate;
    private Button btnDelete;

    private String key;
    private String feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_details);

        key = getIntent().getStringExtra("key");
        feedback = getIntent().getStringExtra("feedback");

        updateFeedback = (EditText) findViewById(R.id.updateFeedTxt);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Feedback feed1 = new Feedback();
                feed1.setFeedBack(updateFeedback.getText().toString());

                new FeedbackDatabaseHelper().updatefeedback(key, feed1, new FeedbackDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Feedback> quizs, List<String> keys) {

                    }

                    @Override
                    public void DataISInserted() {

                    }

                    @Override
                    public void DataIsUpdatd() {

                        Toast.makeText(getApplicationContext(), "Updated Succefully", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new FeedbackDatabaseHelper().deletefeedback(key, new FeedbackDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Feedback> quizs, List<String> keys) {

                    }

                    @Override
                    public void DataISInserted() {

                    }

                    @Override
                    public void DataIsUpdatd() {



                    }

                    @Override
                    public void DataIsDeleted() {

                        Toast.makeText(getApplicationContext(), "Deleted Succefully", Toast.LENGTH_SHORT).show();
                        finish(); return;

                    }
                });
            }
        });



    }
}
