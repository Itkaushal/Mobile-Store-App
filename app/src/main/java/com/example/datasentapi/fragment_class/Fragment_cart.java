package com.example.datasentapi.fragment_class;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datasentapi.R;
import com.example.datasentapi.Interfaces.RecyclerviewInterface;
import com.example.datasentapi.adapter.CustomAdapter;
import com.example.datasentapi.databinding.FragmentCartBinding;
import com.example.datasentapi.databinding.FragmentCartBinding;
import java.util.ArrayList;
public class Fragment_cart extends Fragment implements RecyclerviewInterface {
    FragmentCartBinding fragmentCartBinding;
    static ArrayList<String> product_name = new ArrayList<>();
    static ArrayList<String> product_color = new ArrayList<>();
    static ArrayList<String> product_price = new ArrayList<>();
    static ArrayList<String> product_image_url = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentCartBinding =FragmentCartBinding.inflate(getLayoutInflater());

        product_name.add("Iphone 13");
        product_name.add("Iphone 12");
        product_name.add("Iphone 15");
        product_name.add("Iphone 14");

        product_color.add("Green Sky");
        product_color.add("Black Dark");
        product_color.add("Pink Ossum");
        product_color.add("Grey Platinum");

        product_price.add("₹78,999");
        product_price.add("₹99,999");
        product_price.add("₹100000");
        product_price.add("₹150000");

        product_image_url.add("https://fdn2.gsmarena.com/vv/pics/apple/apple-iphone-15-plus-1.jpg");
        product_image_url.add("https://fdn2.gsmarena.com/vv/pics/apple/apple-iphone-15-pro-1.jpg");
        product_image_url.add("https://fdn2.gsmarena.com/vv/pics/apple/apple-iphone-15-1.jpg");
        product_image_url.add("https://fdn2.gsmarena.com/vv/pics/apple/apple-iphone-15-pro-max-1.jpg");

        fragmentCartBinding.FragmentOrderRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

       CustomAdapter ca = new CustomAdapter(Fragment_cart.this,product_name,product_color,product_price,product_image_url);
       fragmentCartBinding.FragmentOrderRecycler.setAdapter(ca);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        return fragmentCartBinding.getRoot();
    }
}