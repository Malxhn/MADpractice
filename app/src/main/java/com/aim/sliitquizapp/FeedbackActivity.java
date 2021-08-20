package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aim.sliitquizapp.model.Feedback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FeedbackActivity extends AppCompatActivity {

    EditText feedback;
    Button submit, view;

    Feedback feed;

    DatabaseReference dbRef;

    private void clearControl() {
        feedback.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedback = findViewById(R.id.updateFeedTxt);
        submit = findViewById(R.id.btnUpdate);
        view = findViewById(R.id.btnDelete);

        feed = new Feedback();

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback");


                if (TextUtils.isEmpty(feedback.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter a Feedback", Toast.LENGTH_SHORT).show();
                else {
                    feed.setFeedBack(feedback.getText().toString().trim());

                    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    feed.setDate(date);

                    dbRef.push().setValue(feed);

                    Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(FeedbackActivity.this,FeedbackListActivity.class);
                    startActivity(intent);
            }
        });

    }

}



