package com.example.datasentapi.fragment_class;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.datasentapi.MainLayout.LoginPage;
import com.example.datasentapi.databinding.FragmentProfileBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class Fragment_Profile extends Fragment {

    FragmentProfileBinding profileBinding;
    private GoogleSignInClient googleSignInClient;

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "user_info";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IMAGE = "image";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        profileBinding = FragmentProfileBinding.inflate(getLayoutInflater());

        googleSignInClient = GoogleSignIn.getClient(requireContext(), GoogleSignInOptions.DEFAULT_SIGN_IN);

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // Retrieve user data from SharedPreferences
        String username = sharedPreferences.getString(KEY_NAME, null);
        String useremail = sharedPreferences.getString(KEY_EMAIL, null);
        String userimage = sharedPreferences.getString(KEY_IMAGE, null);

        if (username == null || useremail == null || userimage == null) {
            // Retrieve user data from the Intent
            Intent intent = requireActivity().getIntent();
            if (intent != null) {
                username = intent.getStringExtra("name");
                useremail = intent.getStringExtra("email");
                userimage = intent.getStringExtra("image");

                // Save user details to SharedPreferences
                saveUserDetails(username, useremail, userimage);
            }
        }

        if (username != null && useremail != null && userimage != null) {
            profileBinding.tvNameProfile.setText(username);
            profileBinding.tvEmailProfile.setText(useremail);
            Picasso.get().load(userimage).into(profileBinding.imgProfile);
        }

        profileBinding.btnGoogleLgout.setOnClickListener(view -> logOut());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return profileBinding.getRoot();
    }

    private void logOut() {
        googleSignInClient.signOut().addOnCompleteListener(requireActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "SignOut Done", Toast.LENGTH_SHORT).show();

                // Clear SharedPreferences when user log out
                sharedPreferences.edit().clear().apply();

                // Start LoginPage
                Intent intent = new Intent(getContext(), LoginPage.class);
                startActivity(intent);
            }
        });
    }

    // Method to save user detail to SharedPreferences
    public void saveUserDetails(String name, String email, String image) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_IMAGE, image);
        editor.apply();
    }
}
