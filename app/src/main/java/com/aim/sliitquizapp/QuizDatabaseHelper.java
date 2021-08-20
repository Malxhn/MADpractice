package com.aim.sliitquizapp;

import androidx.annotation.NonNull;

import com.aim.sliitquizapp.model.Question;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuizDatabaseHelper {

    private FirebaseDatabase mdatabase;
    private DatabaseReference mreferencequiz;
    private List<Question> quizs=new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Question> quizs,List<String> keys);
        void DataISInserted();
        void DataIsUpdatd();
        void DataIsDeleted();




    }


    public QuizDatabaseHelper() {
        mdatabase=FirebaseDatabase.getInstance();
        mreferencequiz=mdatabase.getReference("DMS");
    }

    public void readquiz(final DataStatus dataStatus){
        mreferencequiz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    quizs.clear();
                    List<String> keys=new ArrayList<>();
                    for (DataSnapshot keyNode:dataSnapshot.getChildren()){
                        keys.add(keyNode.getKey());
                        Question quiz=keyNode.getValue(Question.class);
                        quizs.add(quiz);
                    }
                    dataStatus.DataIsLoaded(quizs,keys);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void updateQuiz(String key,Question quiz,final DataStatus dataStatus ){
        mreferencequiz.child(key).setValue(quiz)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdatd();

                    }
                });
    }

    public void DeleteQuiz(String key,final DataStatus dataStatus){
        mreferencequiz.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });

    }

}
