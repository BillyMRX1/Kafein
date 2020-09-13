package com.putrasamudra.kafein.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.putrasamudra.kafein.MainActivity;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.adapter.CardViewCartAdapter;
import com.putrasamudra.kafein.adapter.SectionsPagerAdapter;
import com.putrasamudra.kafein.model.Cart;

public class CartActivity extends AppCompatActivity {
    private CardViewCartAdapter adapter;
    private ImageButton checkoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        checkoutBtn = findViewById(R.id.imageButton);

        DatabaseReference mbase = FirebaseDatabase.getInstance().getReference().child("Cart");
        RecyclerView rvTypeSeat = findViewById(R.id.rv_cart);
        rvTypeSeat.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(mbase, Cart.class).build();
        adapter = new CardViewCartAdapter(options);
        rvTypeSeat.setAdapter(adapter);

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goCheckout = new Intent(CartActivity.this, CheckoutActivity.class);
                startActivity(goCheckout);
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