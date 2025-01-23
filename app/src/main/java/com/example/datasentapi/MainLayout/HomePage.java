package com.example.datasentapi.MainLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.datasentapi.ProductItemListLayout.ProductItemFourList;
import com.example.datasentapi.ProductItemListLayout.ProductItemOneList;
import com.example.datasentapi.ProductItemListLayout.ProductItemThreeList;
import com.example.datasentapi.ProductItemListLayout.ProductItemTwoList;
import com.example.datasentapi.R;
import com.example.datasentapi.databinding.ActivityHomePageBinding;

import com.example.datasentapi.fragment_class.Fragment_Home;
import com.example.datasentapi.fragment_class.Fragment_Products;
import com.example.datasentapi.fragment_class.Fragment_cart;
import com.example.datasentapi.fragment_class.Fragment_Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


import java.util.Objects;

public class HomePage extends AppCompatActivity {

    ActivityHomePageBinding homePageBinding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePageBinding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(homePageBinding.getRoot());
        mAuth=FirebaseAuth.getInstance();

        Menu menuNav = homePageBinding.bottomnav.getMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.Main_fragment_container, new Fragment_Home()).commit();
        homePageBinding.bottomnav.setOnNavigationItemSelectedListener(bottomNavigationMethod);
    }

    // bottom navigation view creation methode...
    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationMethod =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    //Assigining Fragment as Null
                    Fragment fragment = null;
                    switch (item.getItemId()) {

                        //Shows the Appropriate Fragment by using id as address
                        case R.id.home_menu:
                            fragment = new Fragment_Home();
                            break;
                        case R.id.menu_menu:
                            fragment = new Fragment_Products();
                            break;
                        case R.id.order_menu:
                            fragment = new Fragment_cart();
                            break;
                        case R.id.profile_menu:
                            fragment = new Fragment_Profile();
                            break;

                        default:
                            throw new IllegalStateException("Unexpected value: " + item.getItemId());
                    }
                    //Sets the selected Fragment into the Framelayout
                    getSupportFragmentManager().beginTransaction().replace(R.id.Main_fragment_container, Objects.requireNonNull(fragment)).commit();
                    return true;
                }
            };

    public void ProductItem_one(View view) {
        Intent intent =new Intent(HomePage.this, ProductItemOneList.class);
        startActivity(intent);

    }

    public void ProductItem_two(View view) {
        Intent intent = new Intent(HomePage.this, ProductItemTwoList.class);
        startActivity(intent);
    }

    public void ProductItem_three(View view) {
        Intent intent=new Intent(HomePage.this, ProductItemThreeList.class);
        startActivity(intent);
    }

    public void ProductItem_four(View view) {
        Intent intent=new Intent(HomePage.this, ProductItemFourList.class);
        startActivity(intent);
    }

    public void vivoitemclick(View view) {
        Intent i  = new Intent(HomePage.this, VivoProductActivity.class);
        startActivity(i);
    }

    public void oneplusProductclick(View view) {
        Intent obj = new Intent(HomePage.this,OnePlusProductActivity.class);
        startActivity(obj);
    }

    public void appleProductClick(View view) {
        Intent objs = new Intent(HomePage.this,AppleProductActivity.class);
        startActivity(objs);
    }

    public void oppoProductClick(View view) {
        Intent intentc= new Intent(HomePage.this,OppoProductActivity.class);
        startActivity(intentc);
    }

    public void nothingProductClick(View view) {
        Intent kaushal = new Intent(getApplicationContext(),NothingProductActivity.class);
        startActivity(kaushal);
    }
}