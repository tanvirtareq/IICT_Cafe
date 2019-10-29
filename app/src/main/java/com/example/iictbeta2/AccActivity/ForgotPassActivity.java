package com.example.iictbeta2.AccActivity;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.iictbeta2.R;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextInputLayout getemail;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        toolbar = findViewById(R.id.forgottoolbar);
        getemail = findViewById(R.id.editTextEmail);
        button = findViewById(R.id.go);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reset Password");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getemail.getEditText().getText().toString().trim();


            }
        });
    }
}
