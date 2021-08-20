package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aim.sliitquizapp.model.Question;

import java.util.List;

public class QuizDetails extends AppCompatActivity {

    private EditText mQuestions_editText;
    private EditText mAnswera_editText;
    private EditText mAnswerb_editText;
    private EditText mAnswerc_editText;
    private EditText mAnswerd_editText;
    private EditText mAnswer_editText;
   // private Spinner  mSubject_category_spin;

    private Button mupdate_btn;
    private Button mDelete_btn;
    private Button mBack_btn;

    private String key;
    private String question;
    private String AnswerA;
    private String AnswerB;
    private String AnswerC;
    private String AnswerD;
    private String Answer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details);

        key=getIntent().getStringExtra("key");
        question=getIntent().getStringExtra("question");
        AnswerA=getIntent().getStringExtra("option1");
        AnswerB=getIntent().getStringExtra("option2");
        AnswerC=getIntent().getStringExtra("option3");
        AnswerD=getIntent().getStringExtra("option4");
        Answer=getIntent().getStringExtra("answer");


        mQuestions_editText=(EditText) findViewById(R.id.txtq);
        mQuestions_editText.setText(question);
        mAnswera_editText=(EditText)findViewById(R.id.txta);
        mAnswera_editText.setText(AnswerA);
        mAnswerb_editText=(EditText)findViewById(R.id.txtb);
        mAnswerb_editText.setText(AnswerB);
        mAnswerc_editText=(EditText)findViewById(R.id.txtc);
        mAnswerc_editText.setText(AnswerC);
        mAnswerd_editText=(EditText)findViewById(R.id.txtd);
        mAnswerd_editText.setText(AnswerD);
        mAnswer_editText=(EditText)findViewById(R.id.txtanswer);
        mAnswer_editText.setText(Answer);
       // mSubject_category_spin=(Spinner)findViewById(R.id.spinsub);
       // mSubject_category_spin.setSelection(getIndex_SpinnerItem(mSubject_category_spin));

        mupdate_btn=(Button)findViewById(R.id.btnupdate);
        mDelete_btn=(Button)findViewById(R.id.btnDelete);
        mBack_btn=(Button)findViewById(R.id.Backbtn);

        mupdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Question quiz=new Question();
                quiz.setQuestion(mQuestions_editText.getText().toString());
                quiz.setOption1(mAnswera_editText.getText().toString());
                quiz.setOption2(mAnswerb_editText.getText().toString());
                quiz.setOption3(mAnswerc_editText.getText().toString());
                quiz.setOption4(mAnswerd_editText.getText().toString());
                quiz.setAnswer(mAnswer_editText.getText().toString());
              //  quiz.setChoice(mSubject_category_spin.getSelectedItem().toString());


                new QuizDatabaseHelper().updateQuiz(key, quiz, new QuizDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Question> quizs, List<String> keys) {

                    }

                    @Override
                    public void DataISInserted() {

                    }

                    @Override
                    public void DataIsUpdatd() {
                        Toast.makeText(QuizDetails.this,"Quiz Record has been"+"updated Successfully",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });





            }
        });

        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new QuizDatabaseHelper().DeleteQuiz(key, new QuizDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Question> quizs, List<String> keys) {

                    }

                    @Override
                    public void DataISInserted() {

                    }

                    @Override
                    public void DataIsUpdatd() {

                    }

                    @Override
                    public void DataIsDeleted() {

                        Toast.makeText(QuizDetails.this,"Quiz record has been"+"Deleted successfully",Toast.LENGTH_LONG).show();
                        finish();return;

                    }
                });
            }
        });
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();return;

            }
        });






    }

    private int getIndex_SpinnerItem(Spinner spinner,String item){
        int index=0;
        for(int i=0;i<spinner.getCount();i++){
            if(spinner.getItemAtPosition(i).equals(item)){
                index=i;
                break;
            }

        }
        return index;

    }


}
