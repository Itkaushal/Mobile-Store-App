package com.example.datasentapi.MainLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.datasentapi.ModalClass.ModalAddCart;
import com.example.datasentapi.databinding.ActivityCartproductPageBinding;
import com.example.datasentapi.fragment_class.Fragment_cart;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class cartproduct_page extends AppCompatActivity {
    ActivityCartproductPageBinding cartproductPageBinding;
    Context context;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartproductPageBinding = ActivityCartproductPageBinding.inflate(getLayoutInflater());
        setContentView(cartproductPageBinding.getRoot());

        Intent intent =getIntent();
        if (intent!=null)
        {
           String name =intent.getStringExtra("pname");
           String price =intent.getStringExtra("pprice");
           String desc=intent.getStringExtra("pdesc");
           String imgurl =intent.getStringExtra("pimgurl");

           cartproductPageBinding.pname.setText(name);
           cartproductPageBinding.pprice.setText("₹ "+price);
           cartproductPageBinding.pdesc.setText(desc);
            Picasso.get().load(imgurl).into(cartproductPageBinding.pimg);

        }


    }

    public void buynow(View view) {
       Intent intent =new Intent(this,Payment.class);
       intent.putExtra("pname",cartproductPageBinding.pname.getText().toString());
       intent.putExtra("pprice",cartproductPageBinding.pprice.getText().toString());
       intent.putExtra("pdesc",cartproductPageBinding.pdesc.getText().toString());
       intent.putExtra("pimgurl",getIntent().getStringExtra("pimgurl"));
       startActivity(intent);

    }

    public void addcartbtn(View view) {
        String pname = cartproductPageBinding.pname.getText().toString();
        String pprice = cartproductPageBinding.pprice.getText().toString();
        String pimage = getIntent().getStringExtra("pimgurl");

        databaseReference=FirebaseDatabase.getInstance().getReference();
        ModalAddCart modalAddCart = new ModalAddCart(pname, pprice, pimage);

        String cartItemId = databaseReference.child("AddCart").child("CartItem").push().getKey();

        databaseReference.child("AddCart").child("CartItem").child(cartItemId).setValue(modalAddCart)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(cartproduct_page.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(cartproduct_page.this, "Failed Adding Cart", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}