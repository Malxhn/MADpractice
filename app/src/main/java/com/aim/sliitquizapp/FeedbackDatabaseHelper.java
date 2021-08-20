package com.aim.sliitquizapp;

import androidx.annotation.NonNull;

import com.aim.sliitquizapp.model.Feedback;
import com.aim.sliitquizapp.model.Question;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDatabaseHelper {

    private FirebaseDatabase mdatabase;
    private DatabaseReference mreferencequiz;
    private List<Feedback> quizs=new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Feedback> quizs,List<String> keys);
        void DataISInserted();
        void DataIsUpdatd();
        void DataIsDeleted();

    }

    public FeedbackDatabaseHelper() {
        mdatabase=FirebaseDatabase.getInstance();
        mreferencequiz=mdatabase.getReference("Feedback");
    }

    public void readquiz(final DataStatus dataStatus){
        mreferencequiz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                quizs.clear();
                List<String> keys=new ArrayList<>();
                for (DataSnapshot keyNode:dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Feedback quiz=keyNode.getValue(Feedback.class);
                    quizs.add(quiz);
                }
                dataStatus.DataIsLoaded(quizs,keys);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updatefeedback(String key,Feedback feedback,final DataStatus dataStatus){
        mreferencequiz.child(key).setValue(feedback).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdatd();
            }
        });
    }

    public void deletefeedback(String key,final DataStatus dataStatus){
        mreferencequiz.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }
}
