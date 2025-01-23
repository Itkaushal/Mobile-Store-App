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

import com.example.datasentapi.ModalClass.Modal2;
import com.example.datasentapi.R;
import com.example.datasentapi.MainLayout.cartproduct_page;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public  class FirebaseAdapter2 extends FirebaseRecyclerAdapter<Modal2, FirebaseAdapter2.myviewholder> {
    private final Context context;

    public FirebaseAdapter2(FirebaseRecyclerOptions<Modal2> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Modal2 modal2) {
        holder.name.setText(modal2.getPname());
        holder.price.setText(String.valueOf(modal2.getPprice()));
        holder.desc.setText(modal2.getPdesc());

        String imageUrl = modal2.getPimgurl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(holder.img);
        } else {
            // Handle the case where the image URL is null or empty
            Toast.makeText(holder.itemView.getContext(), "check image url", Toast.LENGTH_SHORT).show();
        }

        holder.itemView.setOnClickListener(view ->{
            Intent intent = new Intent(context, cartproduct_page.class);
            intent.putExtra("pname",modal2.getPname());
            intent.putExtra("pprice",String.valueOf(modal2.getPprice()));
            intent.putExtra("pdesc",modal2.getPdesc());
            intent.putExtra("pimgurl",modal2.getPimgurl());
            context.startActivity(intent);
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rawlayout_productitem_one, parent, false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price, desc;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_rawlay_Product_One);
            name = (TextView) itemView.findViewById(R.id.tv_pname_rawlay_product_one);
            price = (TextView) itemView.findViewById(R.id.tv_pprice_rawlay_product_one);
            desc = (TextView) itemView.findViewById(R.id.tv_pdesc_rawlay_product_one);
        }
    }
}

