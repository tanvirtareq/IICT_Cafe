package com.example.iictbeta2.AccActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.iictbeta2.AfterLoginActivity.LoggedinHomeActivity;
import com.example.iictbeta2.Main2Activity;
import com.example.iictbeta2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout emailField, passField;
    private Toolbar toolbar;

    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    private FirebaseUser current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button gobutton = findViewById(R.id.go);
        Button forgetbutton = findViewById(R.id.forgot);
        emailField = findViewById(R.id.editTextEmail);
        passField = findViewById(R.id.editTextPass);
        toolbar = findViewById(R.id.login_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log in");

        mAuth = FirebaseAuth.getInstance();
        current_user = mAuth.getCurrentUser();

        forgetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goforget = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivity(goforget);
            }
        });

        gobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailField.getEditText().getText().toString().trim();
                final String pass = passField.getEditText().getText().toString();

                if(!checkInvalidInput(email, pass)){
                    progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setTitle("Logging in");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    emailField.setError(null);

                    login(email, pass);


                    if(current_user == null) {
                        login(email, pass);
                    }else {
                        Task<Void> task = current_user.reload();

                        task.addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                if(current_user.isEmailVerified()){
                                    login(email, pass);
                                } else{
                                    progressDialog.dismiss();

                                    Toast.makeText(LoginActivity.this, "Please verify your email!", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }
                }
            }
        });
    }

    private boolean checkValidEmail(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+";
        if(email.matches(emailPattern)){
            return true;
        }
        return false;
    }

    private boolean checkInvalidInput(String email, String pass){

        boolean ret = false;

        if(pass.isEmpty()){
            passField.setError("Field can't be empty!");
            ret = true;
        } else {
            passField.setError(null);
        }

        if(!checkValidEmail(email) || email.isEmpty()){
            if(email.isEmpty()){
                emailField.setError("Field can't be empty!");
            } else {
                emailField.setError("Invalid Email");
            }
            ret = true;
        } else {
            emailField.setError(null);
        }

        return ret;
    }

    private void login(String email, String pass){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();

                    Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_LONG).show();


//                    Intent loggedInIntent = new Intent(LoginActivity.this, LoggedinHomeActivity.class);
//                    loggedInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(loggedInIntent);
//                    finish();

                    Intent homeActivityIntent=new Intent(LoginActivity.this, Main2Activity.class);
                    homeActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(homeActivityIntent);
                    finish();


                } else {
                    progressDialog.dismiss();

                    if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                        Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                    }
                    else if(task.getException() instanceof FirebaseAuthInvalidUserException){
                        Toast.makeText(LoginActivity.this, "User is not registered!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "User is not registered!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
