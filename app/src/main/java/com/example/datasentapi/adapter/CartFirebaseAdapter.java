package com.example.datasentapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datasentapi.ModalClass.ModalAddCart;
import com.example.datasentapi.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartFirebaseAdapter extends FirebaseRecyclerAdapter<ModalAddCart,CartFirebaseAdapter.myviewholder> {
    private final Context context;
    private final   CartItemClickListner cartItemClickListner;


    public CartFirebaseAdapter(@NonNull FirebaseRecyclerOptions<ModalAddCart> options, Context context, CartItemClickListner cartItemClickListner) {
        super(options);
        this.context = context;
        this.cartItemClickListner = cartItemClickListner;
    }

    @Override
    protected void onBindViewHolder(@NonNull CartFirebaseAdapter.myviewholder holder, int position, @NonNull ModalAddCart model) {

        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        String imageUrl = model.getImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(holder.image);
        } else {
            Toast.makeText(holder.itemView.getContext(), "check image url", Toast.LENGTH_SHORT).show();
        }


            holder.deletebtnimage.setOnClickListener(view -> {
               int adapterPosition = holder.getAdapterPosition();
               if (adapterPosition!=RecyclerView.NO_POSITION) {
                   String cartItemKey = getRef(adapterPosition).getKey();
                   cartItemClickListner.onDeleteItemClick(cartItemKey);
               }
               else {
                   Toast.makeText(context, "invalid adapter positoin", Toast.LENGTH_SHORT).show();
               }

            });

    }

    @NonNull
    @Override
    public CartFirebaseAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rawlayout_cart, parent, false);
        return new myviewholder(view);
    }


    public interface CartItemClickListner {
        void onDeleteItemClick(String cartItemKey);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView name,price;
        ImageView deletebtnimage;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.img_cart);
            name=itemView.findViewById(R.id.cart_name);
            price=itemView.findViewById(R.id.cart_price);
            deletebtnimage=itemView.findViewById(R.id.deleteBtn_img);

        }
    }

}
