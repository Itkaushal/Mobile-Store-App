package com.example.datasentapi;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.datasentapi.databinding.ActivityLoginPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {

    ActivityLoginPageBinding loginPageBinding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPageBinding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(loginPageBinding.getRoot());

        mAuth = FirebaseAuth.getInstance();

    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser =mAuth.getCurrentUser();
        if (currentUser!=null)
        {
            reload();
        }
    }

    private void reload() {

    }

    public   void signIn()
    {
        String email = loginPageBinding.etEmailLgn.getText().toString();
        String password = loginPageBinding.etPassLgn.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(LoginPage.this, "login success", Toast.LENGTH_SHORT).show();
                    FirebaseUser user =mAuth.getCurrentUser();
                    updateUI(user);
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

        if (email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "fill the credential!", Toast.LENGTH_SHORT).show();
        }
        else {

            signIn();
            loginPageBinding.etEmailLgn.setText("");
            loginPageBinding.etPassLgn.setText("");
            Intent i = new Intent(getApplicationContext(),HomePage.class);
            startActivity(i);

        }
    }

    public void registergo(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}