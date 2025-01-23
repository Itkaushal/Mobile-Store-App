package com.example.datasentapi.fragment_class;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.datasentapi.ModalClass.ModalScrollview;
import com.example.datasentapi.R;
import com.example.datasentapi.databinding.FragmentHomeBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.cert.CertificateRevokedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Fragment_Home extends Fragment {

    FragmentHomeBinding fragmentHomeBinding;

    ImageSlider imageSlider;

    DatabaseReference databaseReference;


    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentHomeBinding = FragmentHomeBinding.inflate(getLayoutInflater());

        imageSlider = fragmentHomeBinding.imgSlide;

        final ArrayList<SlideModel> remoteimges = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("slider").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data : snapshot.getChildren()) {
                            //remoteimges.add(new SlideModel(Objects.requireNonNull(data.child("url").getValue()).toString(), ScaleTypes.FIT));

                            //imageSlider.setImageList(remoteimges,ScaleTypes.FIT);
                            String url = data.child("url").getValue(String.class);
                            if (url != null) {
                                remoteimges.add(new SlideModel(url, ScaleTypes.FIT));
                                imageSlider.setImageList(remoteimges, ScaleTypes.FIT);
                            } else {
                                Toast.makeText(getContext(), "url not found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Error no data found" + error, Toast.LENGTH_SHORT).show();
                    }
                });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("productHome");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> imageUrls = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Object url = snapshot.child("imageUrls").getValue();
                    if (url instanceof String) {

                        String imageUrl = (String) url;
                        imageUrls.add(imageUrl);
                    }
                }
                loadImages(imageUrls);
            }

            private void loadImages(List<String> imageUrls) {
                if (imageUrls.size() >= 4) {
                    Picasso.get().load(imageUrls.get(0)).into(fragmentHomeBinding.img1Home);
                    Picasso.get().load(imageUrls.get(1)).into(fragmentHomeBinding.img2Home);
                    Picasso.get().load(imageUrls.get(2)).into(fragmentHomeBinding.img3Home);
                    Picasso.get().load(imageUrls.get(3)).into(fragmentHomeBinding.img4Home);
                } else {
                    Toast.makeText(getContext(), "Not Image Found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Error occure!", Toast.LENGTH_SHORT).show();

            }
        });

        // scroll view code horizontal

        DatabaseReference databaseRe = FirebaseDatabase.getInstance().getReference("scrollImage");
        databaseRe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Fetching each item from the database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.exists()) {
                        // Dynamically loading item data
                        String name = snapshot.child("name").getValue(String.class);
                        String imageUrl = snapshot.child("imageUrl").getValue(String.class);

                        if (name != null && imageUrl != null) {
                            switch (snapshot.getKey()) {
                                case "item1":
                                    fragmentHomeBinding.item1Tv.setText(name);
                                    Picasso.get().load(imageUrl).into(fragmentHomeBinding.item1Img);
                                    break;
                                case "item2":
                                    fragmentHomeBinding.item2Tv.setText(name);
                                    Picasso.get().load(imageUrl).into(fragmentHomeBinding.item2Img);
                                    break;
                                case "item3":
                                    fragmentHomeBinding.item3Tv.setText(name);
                                    Picasso.get().load(imageUrl).into(fragmentHomeBinding.item3Img);
                                    break;
                                case "item4":
                                    fragmentHomeBinding.item4Tv.setText(name);
                                    Picasso.get().load(imageUrl).into(fragmentHomeBinding.item4Img);
                                    break;
                                case "item5":
                                    fragmentHomeBinding.item5Tv.setText(name);
                                    Picasso.get().load(imageUrl).into(fragmentHomeBinding.item5Img);
                                    break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error is: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment__home, container, false);
        return fragmentHomeBinding.getRoot();
    }
}