package com.example.datasentapi.fragment_class;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datasentapi.R;
import com.example.datasentapi.databinding.FragmentProfileBinding;

public class Fragment_Profile extends Fragment {

    FragmentProfileBinding profileBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
profileBinding=FragmentProfileBinding.inflate(getLayoutInflater());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__profile, container, false);
        return profileBinding.getRoot();

    }
}