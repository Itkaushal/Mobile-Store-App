package com.example.datasentapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datasentapi.MainLayout.cartproduct_page;
import com.example.datasentapi.ModalClass.VivoModal;
import com.example.datasentapi.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class VivoFirebaseAdapter extends FirebaseRecyclerAdapter<VivoModal,VivoFirebaseAdapter.ViewHolder> {
   private final Context context;

    public VivoFirebaseAdapter(@NonNull FirebaseRecyclerOptions<VivoModal> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull VivoFirebaseAdapter.ViewHolder holder, int position, @NonNull VivoModal model) {

            holder.pname.setText(String.valueOf(model.getPname()));
            holder.pprice.setText(String.valueOf(model.getPprice()));
            holder.pquantity.setText(String.valueOf(model.getQuantity()));
            holder.pdescription.setText(String.valueOf(model.getPdesc()));

        String imageurl = model.getPimage();
        if (imageurl!=null && !imageurl.isEmpty())
        {
            Picasso.get().load(imageurl).into(holder.imageView);
        }
        else {
            Toast.makeText(context, "check image url", Toast.LENGTH_SHORT).show();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, cartproduct_page.class);
                intent.putExtra("pname",model.getPname());
                intent.putExtra("pprice",String.valueOf(model.getPprice()));
                intent.putExtra("pdesc",model.getPdesc());
                intent.putExtra("pimgurl",model.getPimage());
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public VivoFirebaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_layout_vivo,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView pname,pprice,pquantity,pdescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.img_vivo);
            pname=itemView.findViewById(R.id.pname_vivo);
            pprice=itemView.findViewById(R.id.price_vivo);
            pdescription=itemView.findViewById(R.id.pdesc_vivo);
            pquantity=itemView.findViewById(R.id.pquantity_vivo);

        }
    }
}
