package com.example.datasentapi.fragment_class;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.datasentapi.Modal.modal;
import com.example.datasentapi.Modal.modal_home;
import com.example.datasentapi.NoSpacingItemDecoration;
import com.example.datasentapi.R;
import com.example.datasentapi.adapter.FirebaseAdapter;
import com.example.datasentapi.adapter.Firebase_home_adapter;
import com.example.datasentapi.databinding.FragmentHomeBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Home extends Fragment {

    FragmentHomeBinding fragmentHomeBinding;
    RecyclerView recyclerView;
    Firebase_home_adapter firebase_home_adapter;

    ImageSlider imageSlider;


    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
fragmentHomeBinding=FragmentHomeBinding.inflate(getLayoutInflater());
recyclerView=fragmentHomeBinding.recyclerviewFghome;

imageSlider=fragmentHomeBinding.imgSlide;
final ArrayList<SlideModel> remoteimges = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("slider").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data:snapshot.getChildren())
                            remoteimges.add(new SlideModel(data.child("url").getValue().toString(), ScaleTypes.FIT));

                         imageSlider.setImageList(remoteimges,ScaleTypes.FIT);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



recyclerView.addItemDecoration(new NoSpacingItemDecoration());

        fragmentHomeBinding.recyclerviewFghome.setLayoutManager(new GridLayoutManager(getActivity(),2));

                FirebaseRecyclerOptions<modal_home> options =
                new FirebaseRecyclerOptions.Builder<modal_home>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products2"), modal_home.class)
                        .build();

        firebase_home_adapter=new Firebase_home_adapter(options);
        recyclerView.setAdapter(firebase_home_adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        firebase_home_adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase_home_adapter.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      View view = inflater.inflate(R.layout.fragment__home, container, false);
        return fragmentHomeBinding.getRoot();
    }
}