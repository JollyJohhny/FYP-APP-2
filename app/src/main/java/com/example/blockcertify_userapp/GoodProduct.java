package com.example.blockcertify_userapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GoodProduct extends AppCompatActivity {

    TextView txtName,txtPrice,txtDetails,txtManuDate,txtExpireDate;
    ImageView img;
    Bitmap bitmap;
    String TAG = "GenerateQRCode";
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    String ProductId;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_product);

        Toast.makeText(this, "Product is BlockCertify Verified!", Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        ProductId = intent.getStringExtra("PRODUCTID");

        txtName = findViewById(R.id.txtName);
        txtManuDate = findViewById(R.id.txtManuDate);
        txtExpireDate = findViewById(R.id.txtExpiryDate);
        txtPrice = findViewById(R.id.txtPrice);
        txtDetails = findViewById(R.id.txtDetails);

        databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        firebaseAuth = FirebaseAuth.getInstance();


        databaseReference.child(ProductId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String price = dataSnapshot.child("price").getValue(String.class);
                String details = dataSnapshot.child("details").getValue(String.class);
                String ManuDate = dataSnapshot.child("manufacturingDate").getValue(String.class);
                String ExpireDate = dataSnapshot.child("expiryDate").getValue(String.class);



                txtName.setText("Product Name:\n " + name);
                txtPrice.setText("Product Price:\n " +price);
                txtDetails.setText("Product Details:\n "+details);
                txtExpireDate.setText("Expiry Date:\n "+ExpireDate);
                txtManuDate.setText("Manufaturing Date:\n "+ManuDate);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}
