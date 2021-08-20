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

import com.aim.sliitquizapp.model.Feedback;

import java.util.List;

public class FeedbackRecyclerViewConfig {

    private Context mcontext;
    private QuizAdapter mQuizAdapter;

    public void setConfig(RecyclerView recyclerView,Context context,List<Feedback>quizes,List<String> keys){
        mcontext=context;
        mQuizAdapter=new QuizAdapter(quizes,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mQuizAdapter);


    }

    class  QuizItemView extends RecyclerView.ViewHolder {

        private TextView feedbackView;
        private TextView dateView;


        private String key;

        public QuizItemView(ViewGroup parent){
            super(LayoutInflater.from(mcontext).inflate(R.layout.feedback_list_item,parent,false));

            feedbackView=(TextView)itemView.findViewById(R.id.feedbackRecView);
            dateView=(TextView)itemView.findViewById(R.id.DateTxt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext,FeedbackDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("feedback",feedbackView.getText().toString());

                    mcontext.startActivity(intent);

                }
            });

        }
        public void bind(Feedback quiz, String key){
            feedbackView.setText(quiz.getFeedBack());
            dateView.setText(quiz.getDate());
            this.key=key;
        }

    }
    class QuizAdapter extends RecyclerView.Adapter<QuizItemView>{
        private List<Feedback> mQuizList;
        private List<String>mkeys;

        public QuizAdapter(List<Feedback> mQuizList, List<String> mkeys) {
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
