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

import com.aim.sliitquizapp.model.Lesson;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private LessonsAdapter mLessonsAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<Lesson> lessons,List<String> keys){
        mContext = context;
        mLessonsAdapter = new LessonsAdapter(lessons,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mLessonsAdapter);
    }

    class LessonItemView extends RecyclerView.ViewHolder{

        private TextView mAnswer;
        private TextView mQuestion;

        private String key;

        public LessonItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.lessons_list_item,parent,false));

            mAnswer = (TextView) itemView.findViewById(R.id.answer_txtview);
            mQuestion = (TextView) itemView.findViewById(R.id.question_txtview);
            

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,LessonDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("answer",mAnswer.getText().toString());
                    intent.putExtra("question",mQuestion.getText().toString());

                    mContext.startActivity(intent);
                }
            });







        }

        public void bind(Lesson lesson,String key){
            mAnswer.setText(lesson.getAnswer());
            mQuestion.setText(lesson.getQuestion());
            this.key = key;

        }

    }

    class LessonsAdapter extends RecyclerView.Adapter<LessonItemView>{


        private List<Lesson> mLessonList;
        private List<String> mKeys;

        public LessonsAdapter(List<Lesson> mLessonList, List<String> mKeys) {
            this.mLessonList = mLessonList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public LessonItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LessonItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull LessonItemView holder, int position) {
            holder.bind(mLessonList.get(position),mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mLessonList.size();
        }
    }



}
