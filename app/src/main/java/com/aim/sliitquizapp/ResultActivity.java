package com.aim.sliitquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aim.sliitquizapp.model.Question;
import com.aim.sliitquizapp.model.Statistics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    int pStatus = 0;
    int p = 0;
    private Handler handler = new Handler();
    TextView tvP,tvCorrect,tvWrong,tvSkip,tvComment;
    public static final String QLIST = "com.aim.sliitquizapp.QLIST";
    ArrayList<Question> list = new ArrayList<Question>();
    int flag = 0;
    DatabaseReference reference;
    Statistics stat;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvCorrect = findViewById(R.id.tvCorrect);
        tvWrong = findViewById(R.id.tvWrong);
        tvSkip = findViewById(R.id.tvSkip);
        tvP = findViewById(R.id.tvPercentage);
        tvComment = findViewById(R.id.tvComment);

        Intent intent = getIntent();

        list = intent.getParcelableArrayListExtra(QuestionActivity.QLIST);
        String sub = intent.getStringExtra(QuestionActivity.SUB);
        String cr = intent.getStringExtra(QuestionActivity.CORRECT);
        String wr = intent.getStringExtra(QuestionActivity.WRONG);
        String tot = intent.getStringExtra(QuestionActivity.TOTAL);

        int correct = Integer.parseInt(cr);
        int wrong = Integer.parseInt(wr);
        int total = Integer.parseInt(tot);
        int skip = total-correct-wrong;

        final int p = (correct*100)/total;

        tvCorrect.setText(cr);
        tvWrong.setText(wr);
        tvSkip.setText(String.valueOf(skip));

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus<p) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            tvP.setText(pStatus + "%");
                        }
                    });
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        if(p >= 80) {
            tvComment.setText(R.string.tx_best);
        }else if(p >= 50) {
            tvComment.setText(R.string.tx_good);

        }else{
            tvComment.setText(R.string.tx_bad);
        }

        //------------------------ Store score---------------------------------------------------
        if(flag == 0) {
            reference = FirebaseDatabase.getInstance().getReference().child("Statistics");
            stat = new Statistics();
            stat.setCorrect(correct);
            stat.setWrong(wrong);
            stat.setScore(p);
            stat.setSkip(skip);
            stat.setSubject(sub);

            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            stat.setDate(date);

            reference.push().setValue(stat);
            flag++;
        }
    }

    public void answerBtn(View view){
        Intent intent = new Intent(ResultActivity.this,AnswerActivity.class);
        intent.putParcelableArrayListExtra(QLIST, (ArrayList<Question>) list);
        startActivity(intent);
    }

    public void retakeBtn(View v){
        Intent intent = new Intent(ResultActivity.this,SelectquizActivity.class);
        startActivity(intent);
    }

}
