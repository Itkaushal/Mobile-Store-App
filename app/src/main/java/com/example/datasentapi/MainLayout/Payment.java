package com.example.datasentapi.MainLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.datasentapi.ModalClass.ModalUser;
import com.example.datasentapi.R;
import com.example.datasentapi.databinding.ActivityPaymentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class Payment extends AppCompatActivity implements PaymentResultListener {
    ActivityPaymentBinding paymentBinding;
    Checkout checkout = new Checkout();
    final Activity activity=this;
    ModalUser user;
    DatabaseReference databaseReference;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paymentBinding=ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(paymentBinding.getRoot());

         intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("pname");
            String price = intent.getStringExtra("pprice");
            String imgurl = intent.getStringExtra("pimgurl");

            paymentBinding.pname.setText(name);
            paymentBinding.pprice.setText(price);
            Picasso.get().load(imgurl).into(paymentBinding.pimg);
        }

            databaseReference= FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot usersnapshot:snapshot.getChildren())
                    {
                        String userId=usersnapshot.getKey();
                        if (userId!=null && userId.equals(userId))
                            user=usersnapshot.getValue(ModalUser.class);
                        {
                            if (user!=null)
                            {
                                paymentBinding.etNameAddr.setText(user.getName());
                                paymentBinding.etEmailAdd.setText(user.getEmail());
                                paymentBinding.etPhoneAdd.setText(user.getMobile());
                                paymentBinding.etHousnoAddr.setText(user.getHouseno());
                                paymentBinding.etPincodeAddr.setText(user.getPincode());
                                paymentBinding.etStateAddr.setText(user.getState());
                                paymentBinding.etRoadnameAddr.setText(user.getRoadname());
                            }
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(Payment.this, ""+error, Toast.LENGTH_SHORT).show();

                }
            });


        //payment integration
        Checkout.preload(getApplicationContext());

        paymentBinding.btnGetMap.setOnClickListener(view->{
            Intent map = new Intent(getApplicationContext(),GoogleMap.class);
            startActivity(map);
        });

    }
    public void pay(View view) {
       int selectedId=paymentBinding.radiogroup.getCheckedRadioButtonId();
       if (selectedId==paymentBinding.rbtnOne.getId())
       {
           Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
       } else if (selectedId==paymentBinding.rbtnTwo.getId()) {
           Toast.makeText(this, "Work", Toast.LENGTH_SHORT).show();
       }
       else {
           Toast.makeText(this, "Please Select An Option", Toast.LENGTH_SHORT).show();
       }

       startPayment();

    }

    public void startPayment() {
        if (intent != null) {
            checkout.setKeyID("rzp_test_AIHnL4LWr9Dokc");
            checkout.setImage(R.drawable.img);

            try {
                JSONObject options = new JSONObject();
                options.put("name","E-Mobile Store");
                options.put("description", "Reference No. #123456");
                options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
                // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                options.put("theme.color", "#3399cc");
                options.put("currency", "INR");
                options.put("amount", "5000");//pass amount in currency subunits 500x100
                options.put("prefill.email","kumarkaushlendra132@gmail.com");
                options.put("prefill.contact","9953857495");

                JSONObject retryObj = new JSONObject();
                retryObj.put("enabled", true);
                retryObj.put("max_count", 4);
                options.put("retry", retryObj);
                checkout.open(activity, options);

            } catch (Exception e) {
                Log.e("TAG", "Error in starting Razorpay Checkout", e);
            }
        }
        else {
            Log.e("Tag","Intent is null");
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Successful Payment ID"+s, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(activity,OrderSuccess.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "failed and cause is"+s, Toast.LENGTH_SHORT).show();

    }

}