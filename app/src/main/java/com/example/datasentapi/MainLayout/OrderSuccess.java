package com.example.datasentapi.MainLayout;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datasentapi.databinding.ActivityOrderSuccessBinding;

public class OrderSuccess extends AppCompatActivity {
private ActivityOrderSuccessBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
