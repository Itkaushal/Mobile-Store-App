package com.example.datasentapi.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datasentapi.ModalClass.modal;
import com.example.datasentapi.R;
import com.example.datasentapi.MainLayout.cartproduct_page;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class FirebaseAdapter extends FirebaseRecyclerAdapter<modal, FirebaseAdapter.myviewholder> {
    private final Context context;

    public FirebaseAdapter(FirebaseRecyclerOptions<modal> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull modal model) {
        holder.name.setText(model.getPname());
        holder.price.setText(String.valueOf("₹ "+model.getPprice()));
        holder.desc.setText(model.getPdesc());

        String imageUrl = model.getPimgurl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(holder.img);
        } else {

            Toast.makeText(holder.itemView.getContext(), "check image url", Toast.LENGTH_SHORT).show();
        }

        holder.itemView.setOnClickListener(view ->{
            Intent intent = new Intent(context,cartproduct_page.class);
            intent.putExtra("pname",model.getPname());
            intent.putExtra("pprice",String.valueOf(model.getPprice()));
            intent.putExtra("pdesc",model.getPdesc());
            intent.putExtra("pimgurl",model.getPimgurl());
            context.startActivity(intent);
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_raw_products_layout, parent, false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price, desc;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_rawlay);
            name = (TextView) itemView.findViewById(R.id.tv_pname_rawlayout);
            price = (TextView) itemView.findViewById(R.id.tv_pprice_rawlayout);
            desc = (TextView) itemView.findViewById(R.id.tv_pdesc_rawlayout);
        }
    }
}
