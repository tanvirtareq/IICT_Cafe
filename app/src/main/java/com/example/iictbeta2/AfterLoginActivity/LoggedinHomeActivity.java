package com.example.iictbeta2.AfterLoginActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iictbeta2.AccActivity.HomeActivity;
import com.example.iictbeta2.JavaClasses.CustomAdapter;
import com.example.iictbeta2.JavaClasses.FirebaseDatabaseUtility;
import com.example.iictbeta2.JavaClasses.FoodItem;
import com.example.iictbeta2.R;
import com.example.iictbeta2.SettingsRechargeActivities.RechargeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.iictbeta2.R.id.cart_toolbar;
import static com.example.iictbeta2.R.id.mod;

public class LoggedinHomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference ref;

    //private CustomAdapter adapter;
    private FirebaseDatabaseUtility util;

    private Toolbar toolbar;
    private EditText itemName, avStat;
    private String uid;
    private LinearLayout[] l = new LinearLayout[6];

    private ArrayList<FoodItem> foodItems = new ArrayList<>();

    private Button order_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin_home);

        toolbar = findViewById(R.id.logged_in_home_toolbar);
        l[0] = findViewById(R.id.listOne);
        l[1] = findViewById(R.id.listTwo);
        l[2] = findViewById(R.id.listThree);
        l[3] = findViewById(R.id.listFour);
        l[4] = findViewById(R.id.listFive);
        l[5] = findViewById(R.id.listSix);
        order_btn = findViewById(R.id.order);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("IICT Cafe");

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser= mAuth.getCurrentUser();

        ref = FirebaseDatabase.getInstance().getReference().child("food");
        util = new FirebaseDatabaseUtility(ref);

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoggedinHomeActivity.this, CartActivity.class));
            }
        });

        if(currentUser != null){

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    int k = 0;

                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        FoodItem foodItem = ds.getValue(FoodItem.class);

                        if(k < 6){
                            for(int i=0; i<l[k].getChildCount(); i++){
                                /*if(l[k].getChildAt(i) instanceof TextView){
                                    ((TextView) l[k].getChildAt(i)).setText(foodItem.getItemName());
                                }*/
                                if(l[k].getChildAt(i) instanceof LinearLayout){
                                    LinearLayout tmp = (LinearLayout) l[k].getChildAt(i);
                                    for(int j=0; j<2; j++){
                                        if(tmp.getChildAt(j) instanceof TextView){
                                            ((TextView) tmp.getChildAt(j)).setText(foodItem.getItemName());
                                        }
                                        if(tmp.getChildAt(j) instanceof LinearLayout){
                                            LinearLayout tmp_ = (LinearLayout) tmp.getChildAt(j);
                                            for (int p=0; p<1; p++){
                                                if(tmp_.getChildAt(p) instanceof TextView){
                                                    ((TextView) tmp_.getChildAt(p)).setText(foodItem.getAvailability());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        k++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(LoggedinHomeActivity.this, "Could not read user data", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null || !currentUser.isEmailVerified()){
            Intent home = new Intent(LoggedinHomeActivity.this, HomeActivity.class);
            startActivity(home);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.logout_btn){
            sendToStart();
        }
        if(item.getItemId() == R.id.recharge_btn){
            Intent gorecharge = new Intent(LoggedinHomeActivity.this, RechargeActivity.class);
            startActivity(gorecharge);
        }
        if(item.getItemId() == R.id.balance_btn){

            FirebaseDatabase.getInstance().getReference().child("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Integer curr_balance = dataSnapshot.child(uid).child("balance").getValue(Integer.class);

                    AlertDialog.Builder builder = new AlertDialog.Builder(LoggedinHomeActivity.this);
                    builder.setTitle("Current Balance");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.setMessage("Current Balance : " + curr_balance.toString() + " Tk");

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        return true;
    }

    void sendToStart(){
        FirebaseAuth.getInstance().signOut();
        Intent gohome = new Intent(LoggedinHomeActivity.this, HomeActivity.class);
        startActivity(gohome);
        finish();
    }
}
