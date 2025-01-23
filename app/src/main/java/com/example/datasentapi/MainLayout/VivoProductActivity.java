package com.example.datasentapi.MainLayout;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.datasentapi.ModalClass.VivoModal;
import com.example.datasentapi.R;
import com.example.datasentapi.adapter.VivoFirebaseAdapter;
import com.example.datasentapi.databinding.ActivityVivoProductBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class VivoProductActivity extends AppCompatActivity {

    ActivityVivoProductBinding vivoProductBinding;
    RecyclerView recyclerView;
    VivoFirebaseAdapter vivoFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vivoProductBinding=ActivityVivoProductBinding.inflate(getLayoutInflater());
        setContentView(vivoProductBinding.getRoot());

        recyclerView=vivoProductBinding.recyViewVivo;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<VivoModal> options = new FirebaseRecyclerOptions.Builder<VivoModal>()
                .setQuery(getInstance().getReference().child("VivoProducts"), VivoModal.class)
                .build();
        vivoFirebaseAdapter=new VivoFirebaseAdapter(options,this);
        recyclerView.setAdapter(vivoFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        vivoFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vivoFirebaseAdapter.stopListening();
    }
}