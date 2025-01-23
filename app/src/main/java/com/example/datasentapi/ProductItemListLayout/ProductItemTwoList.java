package com.example.datasentapi.ProductItemListLayout;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.datasentapi.ModalClass.Modal2;
import com.example.datasentapi.adapter.FirebaseAdapter2;
import com.example.datasentapi.databinding.ActivityProductItemTwoListBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProductItemTwoList extends AppCompatActivity {

    ActivityProductItemTwoListBinding productItemTwoListBinding;
    FirebaseAdapter2 firebaseAdapter2;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productItemTwoListBinding=ActivityProductItemTwoListBinding.inflate(getLayoutInflater());
        setContentView(productItemTwoListBinding.getRoot());

        recyclerView=productItemTwoListBinding.recyclerview2;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Modal2> options =
                new FirebaseRecyclerOptions.Builder<Modal2>()
                        .setQuery(getInstance().getReference().child("productItemTwo"), Modal2.class)
                        .build();
        firebaseAdapter2= new FirebaseAdapter2(options,this);
        recyclerView.setAdapter(firebaseAdapter2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAdapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAdapter2.stopListening();
    }
}