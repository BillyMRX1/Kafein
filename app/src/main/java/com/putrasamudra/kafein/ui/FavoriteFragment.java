package com.putrasamudra.kafein.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.putrasamudra.kafein.MainActivity;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.adapter.CardViewCafeAdapter;
import com.putrasamudra.kafein.model.Cafe;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView rvCafe;
    private CardViewCafeAdapter adapter;
    private DatabaseReference mbase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        mbase = FirebaseDatabase.getInstance().getReference().child("Cafe");
        rvCafe = view.findViewById(R.id.rv_cafe);
        rvCafe.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<Cafe> options = new FirebaseRecyclerOptions.Builder<Cafe>()
                .setQuery(mbase, Cafe.class).build();
        adapter = new CardViewCafeAdapter(options, getContext());
        rvCafe.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}