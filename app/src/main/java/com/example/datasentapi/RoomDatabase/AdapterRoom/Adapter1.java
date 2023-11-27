package com.example.datasentapi.RoomDatabase.AdapterRoom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.datasentapi.R;
import com.example.datasentapi.RoomDatabase.MyDatabase;
import com.example.datasentapi.RoomDatabase.Product;
import com.example.datasentapi.RoomDatabase.ProductDao;
import com.google.firebase.database.annotations.NotNull;

import java.util.List;

public class Adapter1  extends RecyclerView.Adapter<Adapter1.myviewholder>
{
    List<Product> products;
    TextView rateview;
    private myviewholder holder;

    private int position;

    public Adapter1(List<Product> products, TextView rateview) {
        this.products = products;
        this.rateview= rateview;
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartcheckout_rawlayout,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull myviewholder holder, int position) {
        this.holder = holder;
         int newPosition=holder.getAdapterPosition();

        holder.recid.setText(String.valueOf(products.get(newPosition).getPid()));
        holder.recpname.setText(products.get(newPosition).getPname());
        holder.recpprice.setText(String.valueOf(products.get(newPosition).getPprice()));
        holder.recqnt.setText(String.valueOf(products.get(newPosition).getPquat()));

       /* holder.delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase mdb = Room.databaseBuilder(holder.recid.getContext(),
                        MyDatabase.class, "cart_db").allowMainThreadQueries().build();
                ProductDao productDao = mdb.productDao();

                productDao.deleteById(products.get(position).getPid());
                products.remove(position);
                notifyItemRemoved(position);
                updateprice();
            }
        });*/

       holder.delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase mdb = Room.databaseBuilder(holder.recid.getContext(),
                        MyDatabase.class, "cart_db").allowMainThreadQueries().build();
                ProductDao productDao = mdb.productDao();

                // Use products.get(newPosition) instead of products.get(position)
                productDao.deleteById(products.get(newPosition).getPid());
                products.remove(newPosition);
                notifyItemRemoved(newPosition);
                updateprice();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView recid,recpname,recqnt, recpprice;
        ImageButton delbtn;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            recid=itemView.findViewById(R.id.recid);
            recpname=itemView.findViewById(R.id.recpname);
            recpprice=itemView.findViewById(R.id.recpprice);
            recqnt=itemView.findViewById(R.id.recqnt);
            delbtn=itemView.findViewById(R.id.delbtn);
        }
    }

    public void updateprice()
    {
        int sum=0,i;
        for(i=0;i< products.size();i++)
            sum=sum+(products.get(i).getPprice()*products.get(i).getPquat());

        rateview.setText("Total Amount : INR "+sum);
    }

}
