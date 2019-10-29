package com.example.iictbeta2.SettingsRechargeActivities;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.iictbeta2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class RechargeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout frame;
    private ImageView qr_image;
    private FirebaseUser current_user;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        qr_image = findViewById(R.id.qr);
        frame = findViewById(R.id.loading_frame);
        toolbar = findViewById(R.id.recharge_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Recharge");

        current_user = FirebaseAuth.getInstance().getCurrentUser();

        if(current_user != null) uid = current_user.getUid();

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(uid, BarcodeFormat.QR_CODE, 700, 700);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        qr_image.setImageBitmap(bitmap);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        uid = "UID: " + uid;

        for(int i=0; i< frame.getChildCount(); i++){
            if(frame.getChildAt(i) instanceof TextView){
                ((TextView) frame.getChildAt(i)).setText(uid);
            }
            if(frame.getChildAt(i) instanceof ProgressBar){
                frame.getChildAt(i).setVisibility(View.GONE);
            }

        }

    }
}
