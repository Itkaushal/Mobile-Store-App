package com.example.datasentapi.RoomDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.datasentapi.Payment;
import com.example.datasentapi.RoomDatabase.AdapterRoom.Adapter1;
import com.example.datasentapi.RoomDatabase.MyDatabase;
import com.example.datasentapi.RoomDatabase.Product;
import com.example.datasentapi.RoomDatabase.ProductDao;
import com.example.datasentapi.databinding.ActivityCartCheckoutBinding;

import java.util.List;

public class CartCheckout extends AppCompatActivity {
    ActivityCartCheckoutBinding cartCheckoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartCheckoutBinding=ActivityCartCheckoutBinding.inflate(getLayoutInflater());
        setContentView(cartCheckoutBinding.getRoot());

        getRoomData();
    }

    private void getRoomData() {
        MyDatabase mdb = Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"cart_db").allowMainThreadQueries().build();
        ProductDao productDao = mdb.productDao();

        cartCheckoutBinding.recview.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products =productDao.getAllproduct();

       Adapter1 adapter1=new Adapter1(products,cartCheckoutBinding.rateview);
       cartCheckoutBinding.recview.setAdapter(adapter1);

        int sum =0,i;
        for (i=0;i<products.size();i++)
            sum=sum+products.get(i).getPprice()*products.get(i).getPquat();
        cartCheckoutBinding.rateview.setText("Total Amount : INR "+sum);
    }

    public void checkoutpay(View view) {
        Intent i = new Intent(getApplicationContext(), Payment.class);
        startActivity(i);
    }
}