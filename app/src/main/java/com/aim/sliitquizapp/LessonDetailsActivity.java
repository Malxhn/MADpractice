package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aim.sliitquizapp.model.Lesson;

import java.util.List;

public class LessonDetailsActivity extends AppCompatActivity {

    private EditText mAnsweret;
    private EditText mQuestionet;
    private Button update_btn;
    private Button delete_btn;

    private Button btnsave;

    private String key;
    private String answer;
    private String question;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_details);

        //===========================================================


        final Context context = this;

        Button button = (Button) findViewById(R.id.btnsave);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com"));
                //startActivity(intent);


                String url = mQuestionet.getText().toString();
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(intent);

            }
        });






        //==================================================

        key = getIntent().getStringExtra("key");
        answer = getIntent().getStringExtra("answer");
        question = getIntent().getStringExtra("question");





        mAnsweret = (EditText)findViewById(R.id.et2);
        mAnsweret.setText(answer);
        mQuestionet = (EditText)findViewById(R.id.et1);
        mQuestionet.setText(question);

        update_btn = (Button)findViewById(R.id.btnupdate);
        delete_btn = (Button)findViewById(R.id.btnDelete);


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lesson lesson=new Lesson();

                lesson.setAnswer(mAnsweret.getText().toString());
                lesson.setQuestion(mQuestionet.getText().toString());


                new FirebaseDatabaseHelper().updateLesson(key, lesson, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Lesson> lessons, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                        Toast.makeText(LessonDetailsActivity.this,"record has been updated successfully",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });

            }
        });



        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new FirebaseDatabaseHelper().deleteLesson(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Lesson> lessons, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                        Toast.makeText(LessonDetailsActivity.this,"record has been deleted successfully!",Toast.LENGTH_SHORT).show();
                        finish();return;
                    }
                });



            }
        });




    }
}
