package com.example.datasentapi.MainLayout;
import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.datasentapi.databinding.ActivityLoginPageBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Objects;

public class LoginPage extends AppCompatActivity {
    ActivityLoginPageBinding loginPageBinding;
    FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPageBinding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(loginPageBinding.getRoot());

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        // google se login
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("929622037637-6t6lnbqpf0ghlv453lsln6e6t13b8t44.apps.googleusercontent.com") //  client ID
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        loginPageBinding.googlebtn.setOnClickListener(view ->{
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, 10);

        });
    }
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser =mAuth.getCurrentUser();
        if (currentUser!=null )
        {
            reload();
        }
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null)
        {
            reload();
        }
    }
    private void reload() {
    }

    // sign in with email/password
    public   void signIn()
    {
        String email = loginPageBinding.etEmailLgn.getText().toString();
        String password = loginPageBinding.etPassLgn.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    FirebaseUser firebaseUser =mAuth.getCurrentUser();
                    Toast.makeText(LoginPage.this, "login success", Toast.LENGTH_SHORT).show();
                    updateUI(firebaseUser);
                    Intent i = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginPage.this, "Failed!", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }
    private void updateUI(FirebaseUser user) {

    }
    public void loginsignin(View view) {
        String email = loginPageBinding.etEmailLgn.getText().toString();
        String password = loginPageBinding.etPassLgn.getText().toString();
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "fill the credential!", Toast.LENGTH_SHORT).show();
        }
        else {

            signIn();
            loginPageBinding.etEmailLgn.setText("");
            loginPageBinding.etPassLgn.setText("");
        }
    }
    public void registergo(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    //google sign on activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                String name=account.getDisplayName();
                String email=account.getEmail();
                String url = String.valueOf(account.getPhotoUrl());

                Intent intent = new Intent(getApplicationContext(),HomePage.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("image",url);
                startActivity(intent);
                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();

            } catch (ApiException e) {
                // Sign in failed, log the error and update the UI
                Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                Toast.makeText(this, "Google Sign In Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

}