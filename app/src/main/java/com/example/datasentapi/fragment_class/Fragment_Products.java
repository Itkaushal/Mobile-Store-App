package com.example.datasentapi.fragment_class;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.example.datasentapi.Modal.modal;
import com.example.datasentapi.NoSpacingItemDecoration;
import com.example.datasentapi.R;
import com.example.datasentapi.adapter.FirebaseAdapter;
import com.example.datasentapi.databinding.FragmentProductBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class Fragment_Products extends Fragment {
    FragmentProductBinding fragmentProductBinding;
    RecyclerView recyclerView;
    FirebaseAdapter firebaseAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentProductBinding=FragmentProductBinding.inflate(getLayoutInflater());
        recyclerView = fragmentProductBinding.recyclerView1;
        recyclerView.addItemDecoration(new NoSpacingItemDecoration());
        fragmentProductBinding.recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<modal> options =
                new FirebaseRecyclerOptions.Builder<modal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products"), modal.class)
                        .build();

        firebaseAdapter=new FirebaseAdapter(options);
        recyclerView.setAdapter(firebaseAdapter);

setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseAdapter.stopListening();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView =(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String p) {
                processsearch(p);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String p) {
                processsearch(p);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void processsearch(String p) {

        FirebaseRecyclerOptions<modal> options =
                new FirebaseRecyclerOptions.Builder<modal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products").orderByChild("pname").startAt(p).endAt(p+"\uf8ff"), modal.class)
                        .build();

                        firebaseAdapter=new FirebaseAdapter(options);
                        firebaseAdapter.startListening();
                        recyclerView.setAdapter(firebaseAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__product, container, false);
        return fragmentProductBinding.getRoot();
    }
}