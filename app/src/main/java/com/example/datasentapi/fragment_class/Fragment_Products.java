package com.example.datasentapi.fragment_class;

import static com.example.datasentapi.R.menu.searchmenu;
import static com.google.firebase.database.FirebaseDatabase.getInstance;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.datasentapi.R;
import com.example.datasentapi.adapter.FirebaseAdapter;
import com.example.datasentapi.databinding.FragmentProductBinding;
import com.example.datasentapi.ModalClass.modal;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Fragment_Products extends Fragment {
    FragmentProductBinding fragmentProductBinding;
    RecyclerView recyclerView;
    FirebaseAdapter firebaseAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProductBinding = FragmentProductBinding.inflate(inflater, container, false);
        return fragmentProductBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = fragmentProductBinding.recyclerView1;
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(),2));

        setHasOptionsMenu(true);

        FirebaseRecyclerOptions<modal> options =
                new FirebaseRecyclerOptions.Builder<modal>()
                        .setQuery(getInstance().getReference().child("productAll"), modal.class)
                        .build();

        firebaseAdapter = new FirebaseAdapter(options, this.requireContext());
        recyclerView.setAdapter(firebaseAdapter);
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
        inflater.inflate(searchmenu, menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String p) {
                processSearch(p);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String p) {
                processSearch(p);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void processSearch(String p) {
        FirebaseRecyclerOptions<modal> options =
                new FirebaseRecyclerOptions.Builder<modal>()
                        .setQuery(getInstance().getReference().child("products").orderByChild("pname").startAt(p).endAt(p + "\uf8ff"), modal.class)
                        .build();

        firebaseAdapter.updateOptions(options);
    }
}
