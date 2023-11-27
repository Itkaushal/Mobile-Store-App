package com.example.datasentapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datasentapi.fragment_class.Fragment_cart;
import com.example.datasentapi.Interfaces.RecyclerviewInterface;
import com.example.datasentapi.databinding.CustomLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {
    private RecyclerviewInterface recyclerviewInterface;
    ArrayList<String> product_name;
    ArrayList<String> product_color;
    ArrayList<String> product_price;
    ArrayList<String> product_image_url;
    LayoutInflater inflater;

    private CustomLayoutBinding customLayoutBinding;
    public CustomAdapter(Fragment_cart fragment_cart, ArrayList<String> product_name, ArrayList<String> product_color, ArrayList<String> product_price, ArrayList<String> product_image_url) {
        this.product_name=product_name;
        this.product_color=product_color;
        this.product_price=product_price;
        this.product_image_url=product_image_url;
        inflater=LayoutInflater.from(fragment_cart.getContext());
    }

    @NonNull
    @Override
    public CustomAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        customLayoutBinding=CustomLayoutBinding.inflate(inflater);
        return new viewHolder(customLayoutBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.viewHolder holder, int position) {
        customLayoutBinding.tvPname.setText(product_name.get(position));
        customLayoutBinding.tvPprice.setText(product_color.get(position));
        customLayoutBinding.tvPcolor.setText(product_price.get(position));
        Picasso.get().load(product_image_url.get(position)).into(customLayoutBinding.imgProduct);

    }

    @Override
    public int getItemCount() {
        return product_name.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public viewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
