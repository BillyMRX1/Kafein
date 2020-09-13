package com.putrasamudra.kafein.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.adapter.CardViewCartAdapter;
import com.putrasamudra.kafein.model.Cart;
import com.putrasamudra.kafein.ui.ReservationFragment;

public class CheckoutActivity extends AppCompatActivity {
    private CardViewCartAdapter adapter;
    private ImageButton payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        payBtn = findViewById(R.id.chekout_pay_btn);

        DatabaseReference mbase = FirebaseDatabase.getInstance().getReference().child("Cart");
        RecyclerView rvTypeSeat = findViewById(R.id.rv_cart);
        rvTypeSeat.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(mbase, Cart.class).build();
        adapter = new CardViewCartAdapter(options);
        rvTypeSeat.setAdapter(adapter);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goFinish = new Intent(CheckoutActivity.this, AccountActivity.class);
                startActivity(goFinish);
            }
        });
    }

    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}