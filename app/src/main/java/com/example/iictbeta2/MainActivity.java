package com.example.iictbeta2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.iictbeta2.AccActivity.HomeActivity;
import com.example.iictbeta2.AfterLoginActivity.LoggedinHomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent main2Activity_intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(main2Activity_intent);
            finish();
        }
        else
        {
            Intent home_intent=new Intent(MainActivity.this, HomeActivity.class);
            startActivity(home_intent);
            finish();
        }

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i = new Intent(MainActivity.this, LoggedinHomeActivity.class);
//                startActivity(i);
//                finish();
//            }
//        }, 1300);
    }

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent loggedinhome_intent = new Intent(MainActivity.this, LoggedinHomeActivity.class);
            startActivity(loggedinhome_intent);
            finish();
        }
    }*/
}
