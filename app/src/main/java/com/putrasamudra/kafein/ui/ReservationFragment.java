package com.putrasamudra.kafein.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.adapter.CardViewCafeAdapter;
import com.putrasamudra.kafein.adapter.CardViewInvoiceAdapter;
import com.putrasamudra.kafein.model.Cafe;
import com.putrasamudra.kafein.model.Invoice;

public class ReservationFragment extends Fragment {
    private RecyclerView rvInvoice;
    private CardViewInvoiceAdapter adapter;
    private DatabaseReference mbase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);
        mbase = FirebaseDatabase.getInstance().getReference().child("Invoice");
        rvInvoice = view.findViewById(R.id.rv_invoice);
        rvInvoice.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<Invoice> options = new FirebaseRecyclerOptions.Builder<Invoice>()
                .setQuery(mbase, Invoice.class).build();
        adapter = new CardViewInvoiceAdapter(options);
        rvInvoice.setAdapter(adapter);
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