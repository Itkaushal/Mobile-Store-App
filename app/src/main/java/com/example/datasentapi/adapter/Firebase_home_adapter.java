package com.example.datasentapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datasentapi.Modal.modal_home;
import com.example.datasentapi.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class Firebase_home_adapter extends FirebaseRecyclerAdapter<modal_home,Firebase_home_adapter.myviewholder>{

    public Firebase_home_adapter(@NonNull FirebaseRecyclerOptions<modal_home> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Firebase_home_adapter.myviewholder holder, int position, @NonNull modal_home model) {
        holder.name.setText(model.getPname());
        String imageUrl = model.getPimgurl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(holder.img);
        } else {
            // Handle the case where the image URL is null or empty
            Toast.makeText(holder.itemView.getContext(), "check image url", Toast.LENGTH_SHORT).show();
        }

    }

    @NonNull
    @Override
    public Firebase_home_adapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_raw_layout_home,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.img_rawlay_home);
            name=(TextView) itemView.findViewById(R.id.tv_rawlay_home);
        }
    }
}
