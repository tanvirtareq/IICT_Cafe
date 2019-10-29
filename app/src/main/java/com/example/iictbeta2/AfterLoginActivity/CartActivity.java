package com.example.iictbeta2.AfterLoginActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iictbeta2.JavaClasses.OrderDetails;
import com.example.iictbeta2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public String rice_availability="Available",khichuri_availability="Available" , lentil_availability="Available", eggcurry_availability="Available", pudding_availability="Available",chicken_availability="Available";
    private Button rice_sub_btn;
    private Button khichuri_sub_btn;
    private Button pudding_sub_btn;
    private Button chicken_sub_btn;
    private Button eggcurry_sub_btn;
    private Button lentil_sub_btn;
    private Button rice_add_btn;
    private Button khichuri_add_btn;
    private Button pudding_add_btn;
    private Button chicken_add_btn;
    private Button eggcurry_add_btn;
    private Button lentil_add_btn;
    private Button proceed_btn ;
    public Integer total_price =0;
    public Integer rice_cnt=0;
    public Integer chicken_cnt=0;
    public Integer pudding_cnt=0;
    public Integer khichuri_cnt=0;
    public Integer lentil_cnt=0;
    public Integer eggcurry_cnt=0;
    TextView rice_view;
    TextView chicken_view;
    TextView eggcurry_view;
    TextView lentil_view;
    TextView khichuri_view;
    TextView pudding_view;
    TextView total_view;

    EditText edittableno;

    private DatabaseReference ref;
    private FirebaseUser user;
    private int flag = 0;
    private Integer curr_balance;
    private String display_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cart");

        rice_sub_btn= findViewById(R.id.rice_sub_btn);
        chicken_sub_btn= findViewById(R.id.chicken_sub_btn);
        khichuri_sub_btn= findViewById(R.id.khichuri_sub_btn);
        eggcurry_sub_btn= findViewById(R.id.eggcurry_sub_btn);
        pudding_sub_btn= findViewById(R.id.pudding_sub_btn);
        lentil_sub_btn= findViewById(R.id.lentil_sub_btn);
        rice_add_btn= findViewById(R.id.rice_add_btn);
        chicken_add_btn= findViewById(R.id.chicken_add_btn);
        khichuri_add_btn= findViewById(R.id.khichuri_add_btn);
        eggcurry_add_btn= findViewById(R.id.eggcurry_add_btn);
        pudding_add_btn= findViewById(R.id.pudding_add_btn);
        lentil_add_btn= findViewById(R.id.lentil_add_btn);
        proceed_btn=findViewById(R.id.proceed_btn);

        edittableno = findViewById(R.id.tableno);

        rice_view=findViewById(R.id.rice_quantity);
        chicken_view=findViewById(R.id.chicken_quantity);
        eggcurry_view= findViewById(R.id.eggcurry_quantity);
        lentil_view=findViewById(R.id.lentil_quantity);
        pudding_view=findViewById(R.id.pudding_quantity);
        khichuri_view= findViewById(R.id.khichuri_quantity);
        total_view=findViewById(R.id.total_view);

        rice_view.setText("0");
        chicken_view.setText("0");
        eggcurry_view.setText("0");
        lentil_view.setText("0");
        pudding_view.setText("0");
        khichuri_view.setText("0");
        total_view.setText("0");

        user = FirebaseAuth.getInstance().getCurrentUser();

        ref = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());

        readAvailabilty();

        rice_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rice_availability.equalsIgnoreCase("Available")){
                    total_price+=20;
                    rice_cnt++;
                    String s1=Integer.toString(rice_cnt);
                    rice_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    //Toast.makeText(MainActivity.this , "Rice added  to the cart",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CartActivity.this ,"Not available",Toast.LENGTH_SHORT).show();
                }
            }
        });
        chicken_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chicken_availability.equalsIgnoreCase("Available")){
                    total_price+=40;
                    chicken_cnt++;
                    String s1= Integer.toString(chicken_cnt);
                    chicken_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);

                    // Toast.makeText(MainActivity.this ,"Chicken curry added to the cart", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CartActivity.this ,"Not available",Toast.LENGTH_SHORT).show();
                }
            }
        });
        pudding_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pudding_availability.equalsIgnoreCase("Available")){
                    total_price+=20;
                    pudding_cnt++;
                    String s1= Integer.toString(pudding_cnt);
                    pudding_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    //Toast.makeText(MainActivity.this ,"Pudding added to the cart", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CartActivity.this ,"Not available",Toast.LENGTH_SHORT).show();
                }
            }
        });
        khichuri_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (khichuri_availability.equalsIgnoreCase("Available")){
                    total_price+=25;
                    khichuri_cnt++;
                    String s1= Integer.toString(khichuri_cnt);
                    khichuri_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    //Toast.makeText(MainActivity.this ,"khichuri added to the cart", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CartActivity.this ,"Not available",Toast.LENGTH_SHORT).show();
                }
            }
        });

        lentil_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lentil_availability.equalsIgnoreCase("Available")){
                    total_price+=20;
                    lentil_cnt++;
                    String s1= Integer.toString(lentil_cnt);
                    lentil_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    //Toast.makeText(MainActivity.this ,"Lentil added to the cart", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CartActivity.this ,"Not available",Toast.LENGTH_SHORT).show();
                }
            }
        });
        eggcurry_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eggcurry_availability.equalsIgnoreCase("Available")){
                    total_price+=20;
                    eggcurry_cnt++;
                    String s1= Integer.toString(eggcurry_cnt);
                    eggcurry_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    //Toast.makeText(MainActivity.this ,"Egg curry added to the cart", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CartActivity.this ,"Not available",Toast.LENGTH_SHORT).show();
                }
            }
        });
        rice_sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rice_cnt==0){
                    Toast.makeText(CartActivity.this,"Not in the cart",Toast.LENGTH_SHORT).show();
                }
                else {
                    total_price-=20;
                    rice_cnt--;
                    String s1= Integer.toString(rice_cnt);
                    rice_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    // Toast.makeText(MainActivity.this,"Removed from cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
        chicken_sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chicken_cnt==0){
                    Toast.makeText(CartActivity.this,"Not in the cart",Toast.LENGTH_SHORT).show();
                }
                else{
                    total_price-=40;
                    chicken_cnt--;
                    String s1= Integer.toString(chicken_cnt);
                    chicken_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    //Toast.makeText(MainActivity.this,"Removed from cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
        eggcurry_sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eggcurry_cnt==0){
                    Toast.makeText(CartActivity.this,"Not in the cart",Toast.LENGTH_SHORT).show();
                }
                else {
                    total_price-=20;
                    eggcurry_cnt--;
                    String s1= Integer.toString(eggcurry_cnt);
                    eggcurry_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    //Toast.makeText(MainActivity.this,"Removed from cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
        lentil_sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lentil_cnt==0){
                    Toast.makeText(CartActivity.this,"Not in the cart",Toast.LENGTH_SHORT).show();
                }
                else {
                    total_price-=20;
                    lentil_cnt--;
                    String  s1= Integer.toString(lentil_cnt);
                    lentil_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    // Toast.makeText(MainActivity.this,"Removed from cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
        khichuri_sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (khichuri_cnt==0){
                    Toast.makeText(CartActivity.this,"Not in the cart",Toast.LENGTH_SHORT).show();
                }
                else {
                    total_price-=25;
                    khichuri_cnt--;
                    String s1= Integer.toString(khichuri_cnt);
                    khichuri_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    // Toast.makeText(MainActivity.this,"Removed from cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
        pudding_sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pudding_cnt==0){
                    Toast.makeText(CartActivity.this,"Not in the cart", Toast.LENGTH_SHORT).show();
                }
                else{
                    total_price-=20;
                    pudding_cnt--;
                    String s1= Integer.toString(pudding_cnt);
                    pudding_view.setText(s1);
                    String s2=Integer.toString(total_price);
                    total_view.setText(s2);
                    // Toast.makeText(MainActivity.this,"Removed from cart",Toast.LENGTH_SHORT).show();
                }
            }
        });

        proceed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String tableno = edittableno.getText().toString();

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            if (ds.getKey().equals("balance")){
                                flag++;
                                curr_balance = ds.getValue(Integer.class);
                            }
                            if (ds.getKey().equals("display_name")){
                                flag++;
                                display_name = ds.getValue().toString();
                            }

                            if(flag == 2){
                                if(tableno.isEmpty()){
                                    Toast.makeText(CartActivity.this, "Enter table no", Toast.LENGTH_LONG).show();
                                }
                                if(total_price == 0){
                                    Toast.makeText(CartActivity.this, "Please add something to your cart", Toast.LENGTH_LONG).show();
                                }
                                if(curr_balance-total_price < 0) {
                                    Toast.makeText(CartActivity.this, "Insufficient Balance " + curr_balance.toString(), Toast.LENGTH_LONG).show();
                                }
                                if(curr_balance - total_price > 0 && total_price > 0 && !tableno.isEmpty()){

                                    AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                                    builder.setTitle("Confirm Order");

                                    builder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid())
                                                    .child("balance").setValue(curr_balance -total_price)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()){

                                                                OrderDetails order = new OrderDetails();
                                                                order.setRice(rice_cnt.toString());
                                                                order.setChicken(chicken_cnt.toString());
                                                                order.setPudding(pudding_cnt.toString());
                                                                order.setEggcurry(eggcurry_cnt.toString());
                                                                order.setKhichuri(khichuri_cnt.toString());
                                                                order.setLentil(lentil_cnt.toString());
                                                                order.setTableno(tableno);
                                                                order.setDisplay_name(display_name);
                                                                order.setUid(user.getUid());

                                                                /*HashMap<String, Object> order = new HashMap<>();
                                                                order.put("rice", rice_cnt);
                                                                order.put("chicken", chicken_cnt);
                                                                order.put("pudding", pudding_cnt);
                                                                order.put("egg_curry", eggcurry_cnt);
                                                                order.put("khichuri", khichuri_cnt);
                                                                order.put("lentil", lentil_cnt);
                                                                order.put("table_no", tableno);
                                                                order.put("display_name", display_name);
                                                                order.put("uid", user.getUid());*/

                                                                DatabaseReference newref =  FirebaseDatabase.getInstance().getReference().child("orders");
                                                                final String oid = newref.getKey();
                                                                order.setOid(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                                                FirebaseDatabase.getInstance().getReference().child("orders").child(oid).setValue(order)
                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                if(task.isSuccessful()){

                                                                                    FirebaseDatabase.getInstance().getReference().child("delivery").child(oid).setValue("false").addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                            if(task.isSuccessful()){
                                                                                                Intent intent = new Intent(CartActivity.this, LoggedinHomeActivity.class);
                                                                                                intent.putExtra("oid", oid);
                                                                                                startActivity(intent);
                                                                                                Toast.makeText(CartActivity.this, "Order placed successfully", Toast.LENGTH_LONG).show();
                                                                                            }
                                                                                        }
                                                                                    });


                                                                                }
                                                                                else {
                                                                                    Toast.makeText(CartActivity.this, "Order couldn't be placed", Toast.LENGTH_LONG).show();
                                                                                }
                                                                            }
                                                                        });

                                                                startActivity(new Intent(CartActivity.this, LoggedinHomeActivity.class));
                                                                finish();

                                                            } else {
                                                                Toast.makeText(CartActivity.this, "Operation unsuccessful. Please try again.", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    });

                                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                                    String msg = "Table no : " + tableno + "\n\n";
                                    if(rice_cnt > 0) {
                                        msg += "Rice : " + rice_cnt.toString() + "\n";
                                    }
                                    if(chicken_cnt > 0){
                                        msg += "Chicken : " + chicken_cnt.toString() + "\n";
                                    }
                                    if(pudding_cnt > 0){
                                        msg += "Pudding : " + pudding_cnt.toString() + "\n";
                                    }
                                    if(eggcurry_cnt > 0){
                                        msg += "Egg Curry : " + eggcurry_cnt.toString() + "\n";
                                    }
                                    if(lentil_cnt > 0){
                                        msg += "Lentil : " + lentil_cnt.toString() + "\n";
                                    }
                                    if(khichuri_cnt > 0){
                                        msg += "Khichuri : " + khichuri_cnt.toString() + "\n";
                                    }

                                    msg += "\n" + "Total : " + total_price.toString() + " Tk\n";
                                    msg += "Current Balance : " + curr_balance.toString() + " Tk\n";

                                    builder.setMessage(msg);

                                    AlertDialog dialog = builder.create();
                                    dialog.show();

                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }

    public void readAvailabilty(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("food");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rice_availability = dataSnapshot.child("0").child("availability").getValue().toString();
                chicken_availability = dataSnapshot.child("1").child("availability").getValue().toString();
                eggcurry_availability = dataSnapshot.child("2").child("availability").getValue().toString();
                khichuri_availability = dataSnapshot.child("3").child("availability").getValue().toString();
                pudding_availability = dataSnapshot.child("4").child("availability").getValue().toString();
                lentil_availability = dataSnapshot.child("5").child("availability").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
