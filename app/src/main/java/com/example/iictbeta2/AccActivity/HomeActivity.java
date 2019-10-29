package com.example.iictbeta2.AccActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.iictbeta2.AfterLoginActivity.LoggedinHomeActivity;
import com.example.iictbeta2.MainActivity;
import com.example.iictbeta2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn, signupBtn;
    private TextView welcomeTxt;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeTxt = findViewById(R.id.welcome);
        loginBtn = findViewById(R.id.login);
        signupBtn = findViewById(R.id.signup);

        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login){
            Intent intent_login = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent_login);
        }
        else if(v.getId() == R.id.signup){
            Intent intent_signup = new Intent(HomeActivity.this, SignupActivity.class);
            startActivity(intent_signup);
        }
    }
}
