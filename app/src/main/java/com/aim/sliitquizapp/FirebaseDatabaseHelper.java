package com.aim.sliitquizapp;

import androidx.annotation.NonNull;

import com.aim.sliitquizapp.model.Lesson;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceLessons;
    private List<Lesson> lessons = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Lesson> lessons,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper(){

        mDatabase = FirebaseDatabase.getInstance();
        mReferenceLessons = mDatabase.getReference("Discription");
    }

    public void readLessons(final DataStatus dataStatus){

        mReferenceLessons.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lessons.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){

                    keys.add(keyNode.getKey());
                    Lesson lesson = keyNode.getValue(Lesson.class);
                    lessons.add(lesson);
                }
                dataStatus.DataIsLoaded(lessons,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updateLesson(String key,Lesson lesson,final DataStatus dataStatus){

        mReferenceLessons.child(key).setValue(lesson).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteLesson(String key, final DataStatus dataStatus){

        mReferenceLessons.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                dataStatus.DataIsDeleted();
            }
        });

    }

}
