package com.putrasamudra.kafein;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.putrasamudra.kafein.activity.AccountActivity;
import com.putrasamudra.kafein.adapter.CardViewCafeAdapter;
import com.putrasamudra.kafein.model.Cafe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCafe;
    private CardViewCafeAdapter adapter;
    private DatabaseReference mbase;
    private ImageButton account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account = findViewById(R.id.account);

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goAccount = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(goAccount);
            }
        });

        mbase = FirebaseDatabase.getInstance().getReference().child("Cafe");
        rvCafe = findViewById(R.id.rv_cafe);
        rvCafe.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Cafe> options = new FirebaseRecyclerOptions.Builder<Cafe>()
                .setQuery(mbase, Cafe.class).build();
        adapter = new CardViewCafeAdapter(options, MainActivity.this);
        rvCafe.setAdapter(adapter);
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