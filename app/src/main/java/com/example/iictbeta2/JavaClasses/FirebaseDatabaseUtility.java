package com.example.iictbeta2.JavaClasses;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseDatabaseUtility {
    DatabaseReference rootRef;
    ArrayList<FoodItem> foodItems = new ArrayList<>();

    public FirebaseDatabaseUtility(DatabaseReference ref){
        this.rootRef = ref;
    }

    public boolean writeData(FoodItem item){
        if(item == null){
            return false;
        } else {
            rootRef.child("food").push().setValue(item);
            return true;
        }
    }

    public void readData(DataSnapshot dataSnapshot){
        foodItems.clear();

        for(DataSnapshot ds: dataSnapshot.getChildren()){
            FoodItem foodItem = ds.getValue(FoodItem.class);
            foodItems.add(foodItem);
        }
    }

    public ArrayList<FoodItem> retrieve(){

        rootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                readData(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                readData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return foodItems;
    }

}
