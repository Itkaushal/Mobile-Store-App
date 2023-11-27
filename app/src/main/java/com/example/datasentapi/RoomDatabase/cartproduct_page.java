package com.example.datasentapi.RoomDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.datasentapi.databinding.ActivityCartproductPageBinding;

public class cartproduct_page extends AppCompatActivity {
    ActivityCartproductPageBinding cartproductPageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartproductPageBinding=ActivityCartproductPageBinding.inflate(getLayoutInflater());
        setContentView(cartproductPageBinding.getRoot());

        cartproductPageBinding.btnSaveroomCart.setOnClickListener(view ->{
            MyDatabase db = Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"cart_db").
                    allowMainThreadQueries().build();
            ProductDao productDao= db.productDao();

            Boolean check =productDao.is_exist(Integer.parseInt(cartproductPageBinding.etPidCart.getText().toString()));
            if (check==false)
            {
                int pid = Integer.parseInt(String.valueOf(cartproductPageBinding.etPidCart.getText().toString()));
                String pname = cartproductPageBinding.etPnameCart.getText().toString();
                int pprice = Integer.parseInt(String.valueOf(cartproductPageBinding.etPpriceCart.getText().toString()));
                int pquantity = Integer.parseInt(String.valueOf(cartproductPageBinding.etPquatCart.getText().toString()));
                productDao.insertrecord(new Product(pid,pname,pprice,pquantity));

                cartproductPageBinding.etPidCart.setText("");
                cartproductPageBinding.etPnameCart.setText("");
                cartproductPageBinding.etPpriceCart.setText("");
                cartproductPageBinding.etPquatCart.setText("");
                cartproductPageBinding.tvLbl.setText("Product Inserted Successfully");
            }
            else {
                cartproductPageBinding.etPidCart.setText("");
                cartproductPageBinding.etPnameCart.setText("");
                cartproductPageBinding.etPpriceCart.setText("");
                cartproductPageBinding.etPquatCart.setText("Product Already in cart");
            }
        });
    }

    public void checkcart(View view) {
        Intent intent = new Intent(cartproduct_page.this, CartCheckout.class);
        startActivity(intent);
    }
}