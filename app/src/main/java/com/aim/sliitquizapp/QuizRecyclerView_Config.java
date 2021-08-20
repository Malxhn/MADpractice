package com.aim.sliitquizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aim.sliitquizapp.model.Question;

import java.util.List;

public class QuizRecyclerView_Config {
    private Context mcontext;
    private QuizAdapter mQuizAdapter;


    public void setConfig(RecyclerView recyclerView,Context context,List<Question>quizes,List<String> keys){
        mcontext=context;
        mQuizAdapter=new QuizAdapter(quizes,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mQuizAdapter);


    }

    class  QuizItemView extends RecyclerView.ViewHolder {

        private TextView mQuestion;
        private TextView mAnswerA;
        private TextView mAnswerB;
        private TextView mAnswerC;
        private TextView mAnswerD;
        private TextView mAnswer;

        private String key;

        public QuizItemView(ViewGroup parent){
            super(LayoutInflater.from(mcontext).inflate(R.layout.quiz_list_item,parent,false));

            mQuestion=(TextView)itemView.findViewById(R.id.Question_txt);
            mAnswerA=(TextView)itemView.findViewById(R.id.AnswerA_txt);
            mAnswerB=(TextView)itemView.findViewById(R.id.AnswerB_txt);
            mAnswerC=(TextView)itemView.findViewById(R.id.AnswerC_txt);
            mAnswerD=(TextView)itemView.findViewById(R.id.AnswerD_txt);
            mAnswer=(TextView)itemView.findViewById(R.id.Answertxt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mcontext,QuizDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("question",mQuestion.getText().toString());
                    intent.putExtra("option1",mAnswerA.getText().toString());
                    intent.putExtra("option2",mAnswerB.getText().toString());
                    intent.putExtra("option3",mAnswerC.getText().toString());
                    intent.putExtra("option4",mAnswerD.getText().toString());
                    intent.putExtra("answer",mAnswer.getText().toString());

                    mcontext.startActivity(intent);

                }
            });

        }
        public void bind(Question quiz,String key){
            mQuestion.setText(quiz.getQuestion());
            mAnswerA.setText(quiz.getOption1());
            mAnswerB.setText(quiz.getOption2());
            mAnswerC.setText(quiz.getOption3());
            mAnswerD.setText(quiz.getOption4());
            mAnswer.setText(quiz.getAnswer());
            this.key=key;
        }

        }
    class QuizAdapter extends RecyclerView.Adapter<QuizItemView>{
        private List<Question>mQuizList;
        private List<String>mkeys;

        public QuizAdapter(List<Question> mQuizList, List<String> mkeys) {
            this.mQuizList = mQuizList;
            this.mkeys = mkeys;
        }

        public QuizAdapter() {
            super();
        }

        @NonNull
        @Override
        public QuizItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new QuizItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull QuizItemView holder, int position) {

            holder.bind(mQuizList.get(position),mkeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mQuizList.size();
        }



    }
}
