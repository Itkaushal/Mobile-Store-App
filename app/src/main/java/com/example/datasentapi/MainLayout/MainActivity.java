package com.example.datasentapi.MainLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.datasentapi.ModalClass.ModalUser;
import com.example.datasentapi.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    private FirebaseAuth mAuth;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mAuth =FirebaseAuth.getInstance();


    }
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }

    }


    private void createAccount()
    {
        database= FirebaseDatabase.getInstance().getReference();
        String name= Objects.requireNonNull(mainBinding.etName.getText()).toString();
        String email= Objects.requireNonNull(mainBinding.etEmail.getText()).toString();
        String password= Objects.requireNonNull(mainBinding.etPassword.getText()).toString();
        String mobile= Objects.requireNonNull(mainBinding.etMobile.getText()).toString();
        String pincode= Objects.requireNonNull(mainBinding.etPincode.getText()).toString();
        String state= Objects.requireNonNull(mainBinding.etState.getText()).toString();
        String roadname= Objects.requireNonNull(mainBinding.etRoadname.getText()).toString();
        String housno= Objects.requireNonNull(mainBinding.etHouseno.getText()).toString();
        String userid= Objects.requireNonNull(mainBinding.etUserid.getText()).toString();
        ModalUser user=new ModalUser(name,email,password,mobile,pincode,state,roadname,housno,userid);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setHouseno(housno);
        user.setMobile(mobile);
        user.setPincode(pincode);
        user.setRoadname(roadname);
        user.setState(state);
        user.setUserId(userid);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    FirebaseUser firebaseUser  = mAuth.getCurrentUser();
                    updateUI(firebaseUser);

                }
                else {
                    updateUI(null);

                }
            }
        });
        database.child("users").child("userId").setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        FirebaseUser fuser=mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "User Register," +name, Toast.LENGTH_SHORT).show();
                        updateUI(fuser);
                        Intent intent=new Intent(getApplicationContext(),LoginPage.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Failed Register User!", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });


    }

    public void register(View view) {
        String email =mainBinding.etEmail.getText().toString();
        String password = mainBinding.etPassword.getText().toString();
        if (email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "Fill All Credential!", Toast.LENGTH_SHORT).show();
        }
        else
        {

            createAccount();
            mainBinding.etEmail.setText("");
            mainBinding.etPassword.setText("");
        }
    }

    private void updateUI(FirebaseUser user) {

    }

    public  void reload()
    {

    }

    public void logingo(View view) {
        Intent i = new Intent(MainActivity.this,LoginPage.class);
        startActivity(i);
    }
}