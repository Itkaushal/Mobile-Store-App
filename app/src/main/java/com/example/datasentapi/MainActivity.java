package com.example.datasentapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.datasentapi.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    private FirebaseAuth mAuth;
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
        String email = mainBinding.etEmail.getText().toString();
        String password=mainBinding.etPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {

                    mainBinding.etEmail.setText("");
                    mainBinding.etPassword.setText("");
                    Toast.makeText(MainActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                    FirebaseUser user  = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else {
                    mainBinding.etEmail.setText("");
                    mainBinding.etPassword.setText("");
                    Toast.makeText(MainActivity.this, "Register Failed!", Toast.LENGTH_SHORT).show();
                    updateUI(null);

                }
            }
        });
    }

    public void register(View view) {
        String email =mainBinding.etEmail.getText().toString();
        String password = mainBinding.etPassword.getText().toString();
        if (email.isEmpty() || password.isEmpty())
        {
            mainBinding.etEmail.setText("");
            mainBinding.etPassword.setText("");
            Toast.makeText(this, "Fill All Credential!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mainBinding.etEmail.setText("");
            mainBinding.etPassword.setText("");
            createAccount();
            Intent intent = new Intent(getApplicationContext(),LoginPage.class);
            startActivity(intent);
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