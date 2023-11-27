package com.example.datasentapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.datasentapi.RoomDatabase.cartproduct_page;
import com.example.datasentapi.databinding.ActivityHomePageBinding;
import com.example.datasentapi.fragment_class.Fragment_Home;
import com.example.datasentapi.fragment_class.Fragment_Products;
import com.example.datasentapi.fragment_class.Fragment_cart;
import com.example.datasentapi.fragment_class.Fragment_Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomePage extends AppCompatActivity {
    ActivityHomePageBinding homePageBinding;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePageBinding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(homePageBinding.getRoot());
        mAuth=FirebaseAuth.getInstance();

        FirebaseMessaging.getInstance().subscribeToTopic("shoping")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful())
                        {
                            msg="Failed";
                        }
                    }
                });


        Menu menuNav = homePageBinding.bottomnav.getMenu();

        getSupportFragmentManager().beginTransaction().replace(R.id.Main_fragment_container, new Fragment_Home()).commit();

        homePageBinding.bottomnav.setOnNavigationItemSelectedListener(bottomNavigationMethod);


    }

    public void checkCurrentUser() {
        // [START check_current_user]
        FirebaseUser user =mAuth.getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "Welcome🥰", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "please create account!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

    }

    // bottom navigation view creation methode...
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationMethod =
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

                    }
                    //Sets the selected Fragment into the Framelayout
                    getSupportFragmentManager().beginTransaction().replace(R.id.Main_fragment_container, fragment).commit();
                    return true;
                }
            };

    public void addcart(View view) {
        Intent i = new Intent(HomePage.this, cartproduct_page.class);
        startActivity(i);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "signout", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),LoginPage.class);
        startActivity(i);
    }
}