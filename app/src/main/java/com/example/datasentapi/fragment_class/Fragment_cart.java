package com.example.datasentapi.fragment_class;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datasentapi.MainLayout.Payment;
import com.example.datasentapi.ModalClass.ModalAddCart;
import com.example.datasentapi.adapter.CartFirebaseAdapter;
import com.example.datasentapi.databinding.FragmentCartBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Fragment_cart extends Fragment implements CartFirebaseAdapter.CartItemClickListner {
    private FragmentCartBinding fragmentCartBinding;
    private RecyclerView recyclerView;
    private CartFirebaseAdapter cartFirebaseAdapter;
    private DatabaseReference databaseReference;

    private int totalPrice=0,i;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("AddCart").child("CartItem");
        fragmentCartBinding = FragmentCartBinding.inflate(getLayoutInflater());
        recyclerView = fragmentCartBinding.recyclerviewCart;
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setItemPrefetchEnabled(false);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<ModalAddCart> options =
                new FirebaseRecyclerOptions.Builder<ModalAddCart>()
                        .setQuery(databaseReference, ModalAddCart.class)
                        .build();

        cartFirebaseAdapter = new CartFirebaseAdapter(options, getContext(), this);
        recyclerView.setAdapter(cartFirebaseAdapter);


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                calculateTotalprice(dataSnapshot);
                updateTotalTextview();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("FirebaseError", "Error retrieving data: " + databaseError.getMessage());
            }
        });

    }

    private void calculateTotalprice(DataSnapshot dataSnapshot) {
        totalPrice = 0; // Reset the total price before recalculating
        for (DataSnapshot cartItemSnapshot : dataSnapshot.getChildren()) {
            ModalAddCart cartItem = cartItemSnapshot.getValue(ModalAddCart.class);
            if (cartItem != null) {
                String priceStr = String.valueOf(cartItem.getPrice()); // Get the price as a string

                // Remove all non digit characters
                String cleanedPriceStr = priceStr.replaceAll("[^\\d]", "");

                if (!cleanedPriceStr.isEmpty()) {
                    try {
                        int price = Integer.parseInt(cleanedPriceStr); // Convert cleaned string to integer
                        totalPrice += price; // Add to the total price
                    } catch (NumberFormatException e) {
                        Log.e("PriceConversionError", "Failed to parse price: " + priceStr);
                    }
                }
            }
        }
    }
    private void updateTotalTextview() {

        TextView totalTextView = fragmentCartBinding.rateView;
        totalTextView.setText(String.valueOf(totalPrice));

    }

    @Override
    public void onStart() {
        super.onStart();
        cartFirebaseAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        cartFirebaseAdapter.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onDeleteItemClick(String cartItemKey) {

        DatabaseReference cartItemRef = databaseReference.child(cartItemKey);
        cartItemRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(requireContext(), "Item deleted from cart", Toast.LENGTH_SHORT).show();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        calculateTotalprice(snapshot);
                        updateTotalTextview();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Log.e("Firebase Error","Error retrieving data:"+error.getMessage());
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(requireContext(), "Failed to delete item from cart", Toast.LENGTH_SHORT).show();
            }

        });

        fragmentCartBinding.makeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Payment.class);
                intent.putExtra("price",totalPrice);
                startActivity(intent);
            }
        });

    }

}